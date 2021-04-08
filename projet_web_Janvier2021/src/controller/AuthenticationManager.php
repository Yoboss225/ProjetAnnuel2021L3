<?php

namespace App\src\controller;

use App\src\model\Account\Account;
use App\src\model\DAO\AccountStorage;
use Exception;

class AuthenticationManager
{
    private $accountStorage;

    public function __construct(AccountStorage $accountStorage)
    {
        $this->accountStorage = $accountStorage;
    }

    /**
     * Gère la connexion (vérifie si le couple login/password est correct et stocke
     * le compte correspondant en session), et renvoie true si la connexion a réussi (et false sinon)
     * @param $login
     * @param $password
     * @return bool
     */
    public function connectUser($login, $password)
    {
        //A utiliser par la suite avec une BD
        $account = $this->accountStorage->checkAuth($login, $password);
        if ($account === null) {
            return false;
        }
        $_SESSION['user'] = $account;
        //Ajout de droits pour les utilisateurs connectés
        array_push($_SESSION['droits'], 'creation', 'suppression', 'modification', 'presentation', 'logout');
        return true;
    }

    public function routeAllowed($route) {
        return in_array($route, $_SESSION['droits']);
    }

    /**
     * Droits de base pour tout utilisateur sur le site
     */
    public function setRights()
    {
        if (!key_exists('droits', $_SESSION))
            $_SESSION['droits'] = ['', 'liste', 'login', 'apropos', 'register', 'logout', 'loginExist'];
    }

    /**
     * Indique si l'internaute est connecté·e ou non
     */
    public function isUserConnected()
    {
        return key_exists('user', $_SESSION);
    }

    /**
     * Indique si l'internaute est connecté·e avec le status admin
     */
    public function isAdminConnected()
    {
        return $this->isUserConnected() && $_SESSION['user']->getRole() === Account::ADMIN;
    }

    /**
     *  Renvoie le nom de l'internaute connecté·e ;
     * si l'internaute n'est pas connecté·e, une exception doit être levée
     * @throws Exception si l'utilisateur n'est pas connecté
     */
    public function getUserName()
    {
        if ($this->isUserConnected()) {
            return $_SESSION['user']->getLogin();
        }
        return null;
    }

    public function disconnectUser()
    {
        session_destroy();
        session_start();
        $this->setRights();
    }

    public function getAccount()
    {
        if ($this->isUserConnected()) {
            return $_SESSION['user'];
        } else {
            return null;
        }
    }
}