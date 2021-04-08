<?php

namespace App\src\model\Account;

/**
 * Class Account
 * Représente un compte utilisateur
 */
class Account
{

    const ADMIN = 'admin';
    const USER  = 'user';
    private $login;
    private $password;
    private $role;
    private $createdAt;

    public function __construct($login, $password, $role, $createdAt = null)
    {
        $this->login = $login;
        $this->password = $password;
        $this->role = $role;
        $this->createdAt = $createdAt;
    }

    public function getLogin()
    {
        return $this->login;
    }

    public function getPassword()
    {
        return $this->password;
    }

    public function getRole()
    {
        return $this->role;
    }

    public function getCreatedAt()
    {
        return $this->createdAt;
    }
}