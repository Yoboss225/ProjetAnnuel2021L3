<?php


namespace App\src\model\Account;


class AccountBuilder
{
    const PSEUDO_REF = 'pseudo';
    const PASSWORD_REF = 'password';
    const MIN_LENGTH_LOGIN = 4;
    const MIN_LENGTH_PASSWORD = 4;
    const MAX_LENGTH_LOGIN = 100;
    const MAX_LENGTH_PASSWORD = 60;

    private $error;
    private $data;

    public function __construct($data = [])
    {
        $this->data = $data;
        $this->error = [];
    }

    public function createAccount()
    {
        //TODO : table rôle ?
        return new Account($this->data[self::PSEUDO_REF], $this->data[self::PASSWORD_REF], 'user');
    }

    public function isValid()
    {
        if (key_exists(self::PSEUDO_REF, $this->data)) {
            $this->data[self::PSEUDO_REF] = trim($this->data[self::PSEUDO_REF]);
        }
        //Validation du pseudo
        if (!key_exists(self::PSEUDO_REF, $this->data) || $this->data[self::PSEUDO_REF] === '') {
            $this->error[self::PSEUDO_REF] = 'Champ vide';
        } elseif (mb_strlen($this->data[self::PSEUDO_REF], 'UTF8') > self::MAX_LENGTH_LOGIN) {
            $this->error[self::PSEUDO_REF] = 'Taille du pseudo limitée à ' . self::MAX_LENGTH_LOGIN .' caractères';
        } elseif (mb_strlen($this->data[self::PSEUDO_REF], 'UTF8') < self::MIN_LENGTH_LOGIN) {
            $this->error[self::PSEUDO_REF] = 'Taille du pseudo minimum '. self::MIN_LENGTH_LOGIN .' caractères';
        }

        //Validation du mot de passe
        if (!key_exists(self::PASSWORD_REF, $this->data) || $this->data[self::PASSWORD_REF] === '') {
            $this->error[self::PASSWORD_REF] = 'Champ vide';
        } elseif (mb_strlen($this->data[self::PASSWORD_REF], 'UTF8') > self::MAX_LENGTH_PASSWORD) {
            $this->error[self::PASSWORD_REF] = 'Taille du mot de passe limitée à '. self::MAX_LENGTH_PASSWORD . ' caractères';
        } elseif (mb_strlen($this->data[self::PASSWORD_REF], 'UTF8') < self::MIN_LENGTH_PASSWORD) {
            $this->error[self::PASSWORD_REF] = "Taille du mot de passe minimun de " . self::MIN_LENGTH_PASSWORD . " caractères";
        }

        if (!empty($this->error)) {
            return false;
        }
        return true;
    }

    public function hasPseudoAndPassword()
    {
        //Validation du pseudo
        if (!key_exists(self::PSEUDO_REF, $this->data) || $this->data[self::PSEUDO_REF] === '') {
            $this->error[self::PSEUDO_REF] = 'Champ vide';
        }
        //Validation du mot de passe
        if (!key_exists(self::PASSWORD_REF, $this->data) || $this->data[self::PASSWORD_REF] === '') {
            $this->error[self::PASSWORD_REF] = 'Champ vide';
        }
        if (key_exists(self::PSEUDO_REF, $this->error) || key_exists(self::PASSWORD_REF, $this->error)) {
            return false;
        }
        return true;
    }

    public function getErrorId($name)
    {
        if (key_exists($name, $this->error)) {
            return $this->error[$name];
        }
        return null;
    }

    public function getDataId($name)
    {
        if (key_exists($name, $this->data)) {
            return $this->data[$name];
        }
        return null;
    }
}