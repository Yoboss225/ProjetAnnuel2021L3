<?php


namespace App\src\model\Account;

use App\src\model\DAO\AccountStorage;
use App\src\model\DAO\MySQLDAO;
use PDO;

class AccountStorageMySQL extends MySQLDAO implements AccountStorage
{
    public function __construct(PDO $pdo)
    {
        parent::__construct($pdo);
    }

    public function addUser(Account $user)
    {
        $sql = 'INSERT INTO comptes (login, password, role, createdAt) VALUES (:login, :password, :role, NOW())';
        $res = $this->createQuery($sql, [
            'login' => $user->getLogin(),
            //Hachage du mot de passe pour stockage en base de données
            'password' => password_hash($user->getPassword(), PASSWORD_DEFAULT),
            'role' => $user->getRole()
        ]);
        if ($res->rowCount()) {
            return $this->pdo->lastInsertId();
        }
        return null;
    }

    public function removeUser(Account $user)
    {
        //To delete an user, we need to delete all their objects in tableaux, because they have fk on their columns.
        // TODO: Implement removeUser() method.
    }

    public function checkAuth($login, $password)
    {
        $sql = 'SELECT * FROM comptes WHERE login=:login';
        $res = $this->createQuery($sql, [
            'login' => $login
        ]);
        $account = $res->fetch();
        if ($account !== false) {
            if (password_verify($password, $account['password'])) {
                return new Account($account['login'], $account['password'], $account['role'], $account['createdAt']);
            }
        }
        return null;
    }

    public function exist($login)
    {
        $sql = 'SELECT id FROM comptes WHERE login=:login';
        $res = $this->createQuery($sql, [
            'login' => $login
        ]);
        $line = $res->fetch();
        if ($line) {
            return true;
        }
        return false;
    }
}