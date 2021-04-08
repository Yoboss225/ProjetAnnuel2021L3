<?php

namespace App\src;

use App\src\controller\FrontController;
use App\src\controller\AuthenticationManager;
use App\src\model\DAO\AccountStorage;
use App\src\model\DAO\TableauStorage;
use App\src\view\PrivateView;
use App\src\view\View;
use Exception;

const UPLOAD_DIR = __DIR__ . '/../upload/';

/**
 * Class Router, gère les routes demandées par l'utilisateur pour accéder aux vues correspondantes via le contrôleur
 *
 * Le routeur est le point d'entrée de l'application, il demande des actions au contrôleur suivant la route reçue dans
 * l'URL. Création de la vue, du contrôleur pour le reste de l'application.
 *
 * Le routeur donne également le format des urls de l'application. La vue les utilise car elle ignore leur fonctionnement.
 * Les routes connues mènent à la page demandée et les routes inconnues ou erreurs survenues amènent sur les pages d'erreur.
 */
class Router
{
    /**
     * Démarrage de l'application, création des singletons et appel au contrôleur suivant la route reçue.
     * @param TableauStorage $tableauStorage Méthode de stockage des données de l'application
     * @param AccountStorage $accountStorage
     * @param AuthenticationManager $authenticationManager
     */
    public function run(TableauStorage $tableauStorage, AccountStorage $accountStorage, AuthenticationManager $authenticationManager)
    {
        //Récupération de la route demandée par l'utilisateur
        $route = key_exists('route', $_GET) ? $_GET['route'] : '';
        $id = key_exists('id', $_GET) ? $_GET['id'] : '';

        // Récupération du message feedback stocké dans la session
        $feedback = key_exists('feedback', $_SESSION) ? $_SESSION['feedback'] : '';
        unset($_SESSION['feedback']);

        // Gestion des droits d'accès
        //Gestion des Droits d'accès
        $authenticationManager->setRights();

        //Création de la vue adapté au type d'utilisateur
        if ($authenticationManager->isUserConnected()) {
            $view = new PrivateView($this, $feedback, $authenticationManager->getAccount());
        } else {
            $view = new View($this, $feedback);
        }

        $frontController = new FrontController($view, $tableauStorage, $accountStorage, $authenticationManager);

        //Redirection vers la route demandée par l'utilisateur
        try {
            switch ($route) {
                case '':
                    $frontController->home();
                    break;
                case 'liste':
                    $frontController->showTableauxList();
                    break;
                case 'apropos':
                    $frontController->showAPropos();
                    break;
                case 'register':
                    $frontController->register($_POST, $_SERVER['REQUEST_METHOD']);
                    break;
                case 'login':
                    $frontController->login($_POST, $_SERVER['REQUEST_METHOD']);
                    break;
                case 'creation':
                    $frontController->creationTableau($_POST, $_FILES, $_SERVER['REQUEST_METHOD']);
                    break;
                case 'suppression':
                    $frontController->deleteItem($id, $_SERVER['REQUEST_METHOD']);
                    break;
                case 'modification':
                    $frontController->updateTableau($_POST, $id, $_SERVER['REQUEST_METHOD']);
                    break;
                case 'presentation':
                    $frontController->presentationTableau($id);
                    break;
                case 'logout':
                    $frontController->logout();
                    break;
                case 'loginExist':
                    $frontController->loginExist($_GET);
                    break;
                default:
                    $view->makeUnknownPage();
                    break;
            }
        } catch (Exception $e) {
            //var_dump($e);
            $view->makeErrorPage();
        }
        // Rendu de la vue générée
        $view->render();
    }

    /**
     * Fonction utilitaire POST-redirect-GET. Redirige l'utilisateur vers l'URL donné pour ne pas
     * renvoyer les données POST au rechargement de la page. De plus, une requête POST doit modifier ou ajouter,
     * pas afficher du contenu. Donc redirection See Other.
     * @param $url
     * @param $feedback
     */
    public function POSTredirect($url, $feedback)
    {
        $_SESSION['feedback'] = $feedback;
        header('Location: ' . $url, true, 303);
        exit();
    }

    public function getHomeURL()
    {
        return 'index.php';
    }

    public function getListeURL()
    {
        return 'index.php?route=liste';
    }

    public function getAProposURL()
    {
        return 'index.php?route=apropos';
    }

    public function getCreationTableauURL()
    {
        return 'index.php?route=creation';
    }

    public function getPresentationTableauURL($id)
    {
        return 'index.php?route=presentation&id=' . $id;
    }

    public function getDeleteItemURL($id)
    {
        return "index.php?route=suppression&id=" . $id;
    }

    public function getUpdateTableauURL($id)
    {
        return "index.php?route=modification&id=" . $id;
    }

    public function getRegisterURL()
    {
        return "index.php?route=register";
    }

    public function getSignInURL()
    {
        return 'index.php?route=login';
    }

    public function getLogoutURL()
    {
        return 'index.php?route=logout';
    }
}