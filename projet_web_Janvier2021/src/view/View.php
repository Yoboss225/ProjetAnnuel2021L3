<?php

namespace App\src\view;


use App\src\model\Account\AccountBuilder;
use App\src\model\Tableau\Tableau;
use App\src\model\Tableau\TableauBuilder;
use App\src\Router;

/**
 * Class View
 * Gestion de l'affichage en fonction de l'état du modèle à partir d'un template de base
 * @package App\src\view
 */
class View
{
    /**
     * Référence au routeur pour récupération des urls dynamiquement, la vue
     * ignore la mode de fonctionnement des urls.
     * @var Router Référence au routeur
     */
    protected $router;
    protected $title;
    protected $content;
    protected $menu;
    protected $feedback;
    protected $ajax;

    public function __construct(Router $router, $feedback)
    {
        $this->router = $router;
        $this->content = '';
        $this->title = '';
        $this->menu = [];
        $this->feedback = $feedback;
        $this->ajax = null;
    }

    /************************
     *  Méthodes de rendu   *
     ************************/

    /**
     * Effectue le rendu de la page web à partir du template de base base.php.
     * Les propriétés de la classe correspondent à des parties du templates.
     */
    public function render()
    {
        //Si c'est une requête Ajax, renvoyer les données brutes sans template
        if ($this->ajax) {
            echo $this->ajax;
        } else {
            //Sinon renvoyer un template avec les données générées
            $this->menu = $this->getMenu();
            ob_start();
            include('templates/base.php');
            $data = ob_get_clean();
            echo $data;
        }
    }

    /**
     * Inclusion d'un fichier template comme contenu du template de base.
     * Les données sont contenues dans le tableau data sous forme de clé valeur.
     * @param $filename string Nom du template
     * @param array $data Données à ajouter dans le template
     */
    protected function includeFileAsContent($filename, array $data)
    {
        if (file_exists(__DIR__ . "/templates/" . $filename)) {
            extract($data);
            ob_start();
            include 'templates/' . $filename;
            $this->content = ob_get_clean();
        }
    }

    /**
     * Retourne le menu sous forme d'une liste d'item, avec le lien correspondant, pour les utilisateurs non connectés.
     * @return array
     */
    protected function getMenu()
    {
        return ([
            'Accueil' => $this->router->getHomeURL(),
            'Liste des objets' => $this->router->getListeURL(),
            'A propos' => $this->router->getAProposURL(),
            'Connexion' => $this->router->getSignInURL(),
        ]);
    }

    /**
     * Sécurisation des données utilisateur à l'affichage pour éviter l'exécution de code malveillant
     * @param $data
     * @return string
     */
    public function htmlesc($data)
    {
        return htmlspecialchars(trim($data), ENT_SUBSTITUTE | ENT_HTML5 | ENT_SUBSTITUTE, 'UTF-8');
    }

    /***************************************************
     *  Création des pages demandées par le contrôleur *
     ***************************************************/


    public function makeHomePage()
    {
        $this->title = 'Accueil';
        $this->includeFileAsContent('homeNotConnected.php', []);
    }

    public function makePresentationPageTableau(Tableau $tableau, $id){}

    public function makeTableauxListePage(array $tableaux)
    {
        $this->title = 'Liste des tableaux';
        $this->includeFileAsContent('listePresentation.php', ['tableaux' => $tableaux]);
    }

    public function makeAProposPage()
    {
        $this->title = 'A propos';
        $this->includeFileAsContent('apropos.php', []);
    }

    public function makeCreationTableauPage(TableauBuilder $tableau)
    {
        $this->title = 'Création d\'un tableau';
        $this->includeFileAsContent('form_tableau.php', [
            'link' => $this->router->getCreationTableauURL(),
            'title' => 'Création d\'un tableau',
            'tableau' => $tableau,
            'submit' => 'Envoyer',
            'uploadFile' => true
        ]);
    }

    public function makeCreationTableauSuccessPage($id)
    {
        $this->router->POSTredirect($this->router->getPresentationTableauURL($id), 'Création réussie');
    }

    public function makeCreationTableauFailedPage()
    {
        $this->router->POSTredirect($this->router->getCreationTableauURL(), 'Erreur lors de la création, merci de réessayer');
    }

    public function makeAnimalUpdatePage(TableauBuilder $builder, $id)
    {
        $this->title = 'Modification du tableau';
        $this->includeFileAsContent('form_tableau.php', [
            'link' => $this->router->getUpdateTableauURL($id),
            'title' => 'Modification du tableau',
            'tableau' => $builder,
            'submit' => 'Modifier',
            'uploadFile' => false
        ]);
    }

    public function makeAnimalModifiedPage($id)
    {
        $this->router->POSTredirect($this->router->getPresentationTableauURL($id), 'Modification prise en compte');
    }

    public function makeAnimalNotModifiedPage($id)
    {
        $this->router->POSTredirect($this->router->getUpdateTableauURL($id), 'Certains champs ne sont pas valides');
    }

    public function makeNotAuthorisedUpdatePage($id)
    {
        $this->router->POSTredirect($this->router->getPresentationTableauURL($id), 'Vous n\'êtes pas autorisé à modifier cet objet');
    }


    public function makeItemDeletionPage(Tableau $tableau, $id)
    {
        $this->content = "<div class='single-wrap'><p>Le tableau {$tableau->getTitle()} va être supprimé.</p>";
        $this->content .= "<form action='{$this->router->getDeleteItemURL($id)}' method=\"POST\">";
        $this->content .= '<button>Confirmer</button></form></div>';
    }

    public function makeItemDeletedPage()
    {
        $this->title = "Suppression effectuée";
        $this->content = "<div class='single-wrap'><p>Le tableau a été correctement supprimé.</p>";
        $this->content .= "<p><a href='{$this->router->getHomeURL()}'>Accueil</a></p></div>";
    }

    public function makeNotAuthorisedDeletionPage($id)
    {
        $this->router->POSTredirect($this->router->getPresentationTableauURL($id), 'Vous n\'êtes pas autorisé à supprimer cet objet');
    }

    public function makeLoginPage(AccountBuilder $builder)
    {
        $this->title = 'Création de compte';
        $this->includeFileAsContent('form_register_connect.php', [
            'method' => 'Connexion',
            'validate' => 'Connexion',
            'link' => $this->router->getSignInURL(),
            'user' => $builder,
            'phrase' => "<p>Pas encore de compte ? <a href='{$this->router->getRegisterURL()}'>Créer un compte gratuitement</a></p>",
        ]);
    }

    public function makeConnexionSuccessPage()
    {
        $this->router->POSTredirect($this->router->getHomeURL(), 'Connexion réussie');
    }

    public function makeConnexionFailedPage()
    {
        $this->router->POSTredirect($this->router->getSignInURL(), 'Identifiant ou mot de passe incorrect');
    }

    public function makeRegisterPage(AccountBuilder $builder)
    {

        $this->title = 'Créer un compte';
        $data = ['method' => 'login', 'user' => $builder];
        $this->includeFileAsContent('form_register_connect.php', $data);
        $this->includeFileAsContent('form_register_connect.php', [
            'method' => 'Création de compte',
            'validate' => 'Créer un compte',
            'link' => $this->router->getRegisterURL(),
            'user' => $builder,
            'dispoPseudoJS' => true,
            'phrase' => " <p>Vous possédez déjà un compte ? <a href='{$this->router->getSignInURL()}'>Connexion</a></p>"
        ]);
    }

    public function makeRegisteredPage()
    {
        $this->router->POSTredirect($this->router->getHomeURL(), 'Création de compte réussie');
    }

    public function makeNotRegisteredPage()
    {
        $this->router->POSTredirect($this->router->getRegisterURL(), 'Des erreurs sont présentes dans le formulaire');
    }

    public function makeLoginUsedPage()
    {
        $this->router->POSTredirect($this->router->getRegisterURL(), 'Ce login est déjà utilisé');
    }

    public function makeLogoutPage()
    {
        $this->router->POSTredirect($this->router->getHomeURL(), 'Déconnexion réussie');
    }

    public function makeUnknownPage()
    {
        $this->title = 'Page inconnue';
        $this->content = "<div class='single-wrap'><p>Page inconnue</p></div>";
    }

    public function makeErrorPage()
    {
        $this->title = 'Erreur serveur';
        $this->content = "<div class='single-wrap'><p>Erreur serveur merci de réesayer plus tard</p></div>";
    }

    public function makeNotAuthorizedPage()
    {
        $this->title = 'Accès interdit';
        $this->content = "<div class='single-wrap'><p>Impossible d'accéder à cette page, vous devez être connecté</p>";
        $this->content .= "<p><a href='{$this->router->getSignInURL()}'>Connexion</a></p></div>";
    }

    public function sendAjaxMsg(string $string)
    {
        $this->ajax = $string;
    }
}
