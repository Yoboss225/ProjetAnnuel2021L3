<?php


namespace App\src\view;

use App\src\model\Account\Account;
use App\src\model\Tableau\Tableau;
use App\src\Router;

/**
 * Vue pour les utilisateurs connectés, affiche un menu différent
 * Class PrivateView
 * @package App\src\view
 */
class PrivateView extends View
{
    private $account;

    public function __construct(Router $routeur, $feedback, Account $account)
    {
        parent::__construct($routeur, $feedback);
        $this->account = $account;
    }

    public function makeHomePage()
    {
        $this->title = 'Accueil';
        $this->includeFileAsContent("homeConnected.php",[
            "account"=>$this->account,
            "message"=>"Bonjour je suis "
        ]);
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
            'Ajouter tableau' => $this->router->getCreationTableauURL(),
            'Déconnexion' => $this->router->getLogoutURL()
        ]);
    }

    public function makePresentationPageTableau(Tableau $tableau, $id)
    {
        $this->title = $tableau->getTitle();
        // Si utilisateur connecté et à qui appartient le tableau, il peut modifier et supprimer
        //Ou si admin connecté
        $action = $tableau->getCreationUser() === $this->account->getLogin() || $this->account->getRole() === Account::ADMIN;
        $this->includeFileAsContent('presentation.php', [
            'tableau' => $tableau,
            'id' => $id,
            'actions' => $action
        ]);
    }
}