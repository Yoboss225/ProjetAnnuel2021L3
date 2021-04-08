<?php

namespace App\src\controller;

//Classes nécessaires
use App\src\model\Account\AccountBuilder;
use App\src\model\DAO\AccountStorage;
use App\src\model\DAO\TableauStorage;
use App\src\model\Tableau\TableauBuilder;
use App\src\view\View;
use const App\src\UPLOAD_DIR;

/**
 * Class FrontController, gère les actions qui peuvent être effectuées par les utilisateurs
 * @package App\src\Controller
 */
class FrontController
{

    /** @var View $view Vue appliquée au modèle */
    protected $view;

    /** @var TableauStorage $tableauStorage Méthode de stockage des tableaux */
    protected $tableauStorage;

    /** @var AccountStorage $accountStorage Méthode de stockage des comptes utilisateurs */
    protected $accountStorage;

    /** @var AuthenticationManager $authenticationManager Gestion de la connexion des utilisateurs */
    protected $authenticationManager;

    /** @var AccountBuilder $currentAccountBuilder Créateur de compte enregistré dans la session */
    protected $currentAccountBuilder;

    /** @var array $currentTableauBuilder Créateur de tableau enregistré en session */
    private $currentTableauBuilder = [];

    public function __construct(View $view, TableauStorage $tableauStorage, AccountStorage $accountStorage, AuthenticationManager $authenticationManager)
    {
        $this->view = $view;
        $this->tableauStorage = $tableauStorage;
        $this->authenticationManager = $authenticationManager;
        $this->accountStorage = $accountStorage;
        $this->currentAccountBuilder = key_exists('accountBuilder', $_SESSION) ? $_SESSION['accountBuilder'] : null;
        $this->currentTableauBuilder = key_exists('tableauBuilder', $_SESSION) ? $_SESSION['tableauBuilder'] : [];
    }

    public function __destruct()
    {
        $_SESSION['accountBuilder'] = $this->currentAccountBuilder;
        $_SESSION['tableauBuilder'] = $this->currentTableauBuilder;
    }

    /************************************
     * Actions demandées par le routeur *
     ***********************************/

    public function home()
    {
        if (!$this->authenticationManager->routeAllowed('')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }
        $this->view->makeHomePage();
    }

    public function showTableauxList()
    {
        if (!$this->authenticationManager->routeAllowed('liste')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }
        $tableaux = $this->tableauStorage->readAll();
        $this->view->makeTableauxListePage($tableaux);
    }

    public function showAPropos()
    {
        if (!$this->authenticationManager->routeAllowed('apropos')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        $this->view->makeAProposPage();
    }

    /**
     * Demande de création de compte, si données soumises, création du compte en base de données
     * @param array $post Données post envoyées par l'utilisateur
     * @param $requestMethod string Méthode de demande de la page au serveur
     */
    public function register(array $post, $requestMethod)
    {
        if (!$this->authenticationManager->routeAllowed('register')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        if ($requestMethod === 'GET') {
            if ($this->currentAccountBuilder === null) {
                $this->currentAccountBuilder = new AccountBuilder();
            }
            $this->view->makeRegisterPage($this->currentAccountBuilder);
            //On vide le currentBuilder en cours
            $this->currentAccountBuilder = null;
        } elseif ($requestMethod === 'POST') {
            $this->currentAccountBuilder = new AccountBuilder($post);
            if ($this->currentAccountBuilder->isValid()) {
                $account = $this->currentAccountBuilder->createAccount();
                $this->currentAccountBuilder = null;
                //On vérifie que le nom n'est pas déjà pris
                if ($this->accountStorage->exist($account->getLogin())) {
                    $this->view->makeLoginUsedPage();
                } else {
                    //Ajout en bd de l'utilisateur
                    $id = $this->accountStorage->addUser($account);
                    //connexion de l'utilisateur directement apres creation de compte
                    $this->authenticationManager->connectUser($account->getLogin(),$account->getPassword());
                    if ($id !== null) {
                        $this->view->makeRegisteredPage();
                    }
                }
            }
            $this->view->makeNotRegisteredPage();
        } else {
            $this->view->makeUnknownPage();
        }
    }

    public function login(array $post, $requestMethod)
    {
        if (!$this->authenticationManager->routeAllowed('login')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        if ($requestMethod === 'GET') {
            $this->view->makeLoginPage(new AccountBuilder());
        } elseif ($requestMethod === 'POST') {
            $builder = new AccountBuilder($post);
            if ($builder->hasPseudoAndPassword()) {
                //On retire les espaces avant et après le pseudo
                $pseudo = trim($builder->getDataId(AccountBuilder::PSEUDO_REF));
                $password = $builder->getDataId(AccountBuilder::PASSWORD_REF);
                //Tentative de connexion avec couple login / mot de passe
                $account = $this->authenticationManager->connectUser($pseudo, $password);
                if ($account !== false ) {
                    $this->view->makeConnexionSuccessPage();
                }
            }
            $this->view->makeConnexionFailedPage();
        } else {
            $this->view->makeUnknownPage();
        }
    }

    public function logout()
    {
        if (!$this->authenticationManager->routeAllowed('logout')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        $this->authenticationManager->disconnectUser();
        $this->view->makeLogoutPage();
    }

    /**
     * Création d'un tableau
     * @param array $post
     * @param array $files
     * @param string $requestMethod Méthode de la requête
     */
    public function creationTableau(array $post, array $files, string $requestMethod)
    {
        if (!$this->authenticationManager->routeAllowed('creation')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        if ($requestMethod === 'GET') {
            $this->view->makeCreationTableauPage(new TableauBuilder(null));
        } elseif ($requestMethod === 'POST') {
            $builder = new TableauBuilder($post, $files);
            //Attribution de l'auteur en fonction de l'utilisateur connecté lors de la création
            $builder->setCreationUser($this->authenticationManager->getUserName());
            if ($builder->isValid()) {
                $id = $this->tableauStorage->insert($builder->createTableau());
                if ($id) {
                    $this->view->makeCreationTableauSuccessPage($id);
                } else {
                    $this->view->makeCreationTableauFailedPage();
                }
            } else {
                $this->view->makeCreationTableauPage($builder);
            }
        } else {
            $this->view->makeUnknownPage();
        }
    }

    public function deleteItem($tableauId, $requestMethod)
    {
        if (!$this->authenticationManager->routeAllowed('suppression')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        //Restriction de la suppression uniquement au propriétaire et aux admin
        $item = $this->tableauStorage->read($tableauId);
        if ($item === null) {
            $this->view->makeUnknownPage();
            return;
        } else {
            if ($item->getCreationUser() !== $this->authenticationManager->getUserName() && !$this->authenticationManager->isAdminConnected()) {
                $this->view->makeNotAuthorisedDeletionPage($tableauId);
            }
        }

        if ($requestMethod === 'GET') {
            $this->view->makeItemDeletionPage($item, $tableauId);
        } elseif ($requestMethod === 'POST') {
            $this->view->makeItemDeletedPage();
            if ($this->tableauStorage->delete($tableauId)) {
                //Suppression de l'image dans le dossier upload
                if(file_exists(UPLOAD_DIR . $item->getMainPicture())) {
                    unlink(UPLOAD_DIR . $item->getMainPicture());
                }
                $this->view->makeItemDeletedPage();
            } else {
                $this->view->makeErrorPage();
            }
        } else {
            $this->view->makeUnknownPage();
        }
    }

    public function updateTableau(array $post, $id, $requestMethod)
    {
        if (!$this->authenticationManager->routeAllowed('modification')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }
        //Restriction de la modification uniquement au propriétaire et aux admins
        $item = $this->tableauStorage->read($id);
        if ($item === null) {
            $this->view->makeUnknownPage();
            return;
        } else {
            if ($item->getCreationUser() !== $this->authenticationManager->getUserName() && !$this->authenticationManager->isAdminConnected()) {
                $this->view->makeNotAuthorisedUpdatePage($id);
            }
        }

        if ($requestMethod === 'GET') {
            if (key_exists($id, $this->currentTableauBuilder)){
                $this->view->makeAnimalUpdatePage($this->currentTableauBuilder[$id], $id);
            } else {
                //Création d'une vue avec builder comme modèle
                $builder = TableauBuilder::createTableauBuilderFromTableau($item);
                $this->currentTableauBuilder[$id] = $builder;
                $this->view->makeAnimalUpdatePage($builder, $id);
            }
        } elseif ($requestMethod === 'POST') {
            //Soumission des modifications
            //Nouveau builder et validation des données
            $builder = new TableauBuilder($post);
            $builder->setCreationUser($item->getCreationUser());
            $builder->setMainPicture($item->getMainPicture());
            if ($builder->isValid()) {
                //Modification de l'animal
                $modif = $builder->createTableau();
                if ($this->tableauStorage->update($modif, $id)) {
                    unset($this->currentTableauBuilder[$id]);
                    $this->view->makeAnimalModifiedPage($id);
                } else {
                    $this->view->makeErrorPage();
                }
            } else {
                //Formulaire invalide
                $this->currentTableauBuilder[$id] = $builder;
                $this->view->makeAnimalNotModifiedPage($id);
            }
        } else {
            $this->view->makeUnknownPage();
        }
    }

    /**
     * Demande l'affichage des informations d'un tableau
     * @param $id string Identifiant du tableau en question
     */
    public function presentationTableau($id)
    {
        if (!$this->authenticationManager->routeAllowed('presentation')) {
            $this->view->makeNotAuthorizedPage();
            return;
        }

        if ($tableau = $this->tableauStorage->read($id)) {
            $this->view->makePresentationPageTableau($tableau, $id);
        } else {
            $this->view->makeUnknownPage();
        }
    }

    /**
     * Réponse requête Ajax
     * Renvoie true si le login dans $_GET['value'] existe en BDD, false sinon
     * @param array $url
     */
    public function loginExist(array $url)
    {
        if (key_exists('value', $url)) {
            $pseudo = $url['value'];
            if ($this->accountStorage->exist($pseudo)) {
                $this->view->sendAjaxMsg('true');
                return;
            }
        }
        $this->view->sendAjaxMsg('false');
    }
}