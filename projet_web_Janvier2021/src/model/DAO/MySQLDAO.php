<?php


namespace App\src\model\DAO;


abstract class MySQLDAO
{
    protected $pdo;

    public function __construct(\PDO $pdo)
    {
        $this->pdo = $pdo;
    }

    public function createQuery($sql, $data = [])
    {
        if ($data) {
            $res = $this->pdo->prepare($sql);
            $res->execute($data);
        } else {
            $res = $this->pdo->query($sql);
        }
        $res->setFetchMode(\PDO::FETCH_ASSOC);
        return $res;
    }
}