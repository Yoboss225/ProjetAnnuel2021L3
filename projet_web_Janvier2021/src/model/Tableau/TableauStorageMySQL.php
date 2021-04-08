<?php


namespace App\src\model\Tableau;

use App\src\model\DAO\MySQLDAO;
use App\src\model\DAO\TableauStorage;
use Exception;
use PDO;

class TableauStorageMySQL extends MySQLDAO implements TableauStorage
{
    public function __construct(PDO $pdo)
    {
        parent::__construct($pdo);
    }

    public function insert(Tableau $tableau)
    {
        $sql = 'INSERT INTO tableaux  (title, content, artist, user_id, main_picture, createdAt) VALUES  (:titre, :content, :artist, (SELECT id FROM comptes WHERE login=:user), :photo, NOW())';
        $this->createQuery($sql, [
            'titre' => $tableau->getTitle(),
            'content' => $tableau->getContent(),
            'artist' => $tableau->getArtist(),
            'photo' => $tableau->getMainPicture(),
            'user' => $tableau->getCreationUser()
        ]);
        return $this->pdo->lastInsertId();
    }

    public function read($id)
    {
        $sql = 'SELECT tableaux.title, tableaux.content, tableaux.artist, login, tableaux.main_picture, tableaux.createdAt FROM tableaux LEFT JOIN comptes C on C.id = tableaux.user_id WHERE tableaux.id = :id;';
        $res = $this->createQuery($sql, ['id' => $id]);
        if ($data = $res->fetch()) {
            return new Tableau($data['title'], $data['content'], $data['artist'], $data['login'], $data['main_picture'], $data['createdAt']);
        }
        return null;
    }

    public function readAll()
    {
        $tableaux = [];
        $sql = 'SELECT tableaux.id, tableaux.title, tableaux.content, tableaux.artist, C.login, tableaux.main_picture, tableaux.createdAt FROM tableaux LEFT JOIN comptes C on C.id = tableaux.user_id ORDER BY tableaux.createdAt DESC;';
        $res = $this->createQuery($sql);
        if ($res) {
            while ($data = $res->fetch()) {
                $id = $data['id'];
                $tableaux[$id] = new Tableau($data['title'], $data['content'], $data['artist'], $data['login'], $data['main_picture'], $data['createdAt']);
            }
            return $tableaux;
        }
        throw new Exception();
    }

    public function delete($id)
    {
        $sql = 'DELETE FROM tableaux WHERE id=:id';
        $res = $this->createQuery($sql, ['id' => $id]);
        return $res->rowCount();
    }

    /**
     * @param Tableau $modif
     * @param $id
     * @return mixed
     */
    public function update(Tableau $modif, $id)
    {
        $sql = 'UPDATE tableaux SET tableaux.title=:title, tableaux.content=:content, tableaux.artist=:artist, tableaux.main_picture=:main_picture WHERE id=:id';
        $res = $this->createQuery($sql, [
            'title' => $modif->getTitle(),
            'content' => $modif->getContent(),
            'artist' => $modif->getArtist(),
            'main_picture' => $modif->getMainPicture(),
            'id' => $id
        ]);
        return $res;
    }
}