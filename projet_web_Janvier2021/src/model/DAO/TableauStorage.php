<?php


namespace App\src\model\DAO;

use App\src\model\Tableau\Tableau;

interface TableauStorage
{
    public function insert(Tableau $tableau);

    public function read($id);

    public function readAll();

    public function delete($id);

    public function update(Tableau $modif, $id);
}