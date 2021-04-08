<?php

namespace App\src\model\DAO;
use App\src\model\Account\Account;

/**
 * Interface AccountStorage
 * Défini les méthodes pour manipuler les comptes en BDD
 */
interface AccountStorage
{
    /**
     * Renvoie l'instance de Account correspondant au couple login mot de passe
     * s'il est correct. Null dans le cas contraire.
     * @param $login string Login de l'utilisateur
     * @param $password string Mot de passe de connexion non haché
     * @return mixed Account|null Compte utilisateur
     */
    public function checkAuth($login, $password);

    /**
     * Ajoute un utilisateur dans la base de données, renvoie l'identifiant crée ou null si échoue.
     * @param Account $user
     * @return mixed
     */
    public function addUser(Account $user);

    /**
     * Supprime un utilisateur de la base de données, renvoie true si bien supprimé, false dans le cas contraire
     * @param Account $user
     * @return mixed
     */
    public function removeUser(Account $user);

    /**
     * Indique si le login donné est déjà présent en base de donnée
     * @param $login
     * @return bool Vrai si le login existe déjà
     */
    public function exist($login);
}