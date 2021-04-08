<?php


namespace App\src\model\Tableau;

use const App\src\UPLOAD_DIR;

/**
 * Class TableauBuilder
 * Classe de construction d'un tableau. Les données peuvent être invalides car saisies
 * par l'utilisateur. Permet d'encapsuler les données envoyées dans le formulaire.
 * Un builder permet de valider les données pour convenir au modèle.
 * @package App\src\model
 */
class TableauBuilder
{
    const TITLE_REF = 'title';
    const CONTENT_REF = 'content';
    const ARTIST = 'artist';
    const USER = 'user';
    const IMG_REF = 'photo';

    private $data;
    private $files;
    private $error;

    public function __construct($data, $files=[])
    {
        $this->data = $data;
        $this->files = $files;
        $this->error = [];
    }

    public static function createTableauBuilderFromTableau(Tableau $tableau)
    {
        return new TableauBuilder([
            self::TITLE_REF => $tableau->getTitle(),
            self::CONTENT_REF => $tableau->getContent(),
            self::ARTIST => $tableau->getArtist(),
            self::USER => $tableau->getCreationUser(),
            self::IMG_REF => $tableau->getMainPicture()
            ]);
    }

    public function createTableau()
    {
        return new Tableau($this->data[self::TITLE_REF], $this->data[self::CONTENT_REF], $this->data[self::ARTIST], $this->data[self::USER], $this->data[self::IMG_REF], null);
    }

    public function isValid()
    {
        if(!key_exists(self::TITLE_REF,$this->data) || $this->data[self::TITLE_REF] === "" ){
            $this->error[self::TITLE_REF] ="Le champ est vide";
        }
        else if(mb_strlen($this->data[self::TITLE_REF], 'UTF8') > 40){
            $this->error[self::TITLE_REF] = "La taille maximum du titre est de 40 caracteres";
        }
        else if(mb_strlen($this->data[self::TITLE_REF], 'UTF8') < 4){
            $this->error[self::TITLE_REF] = "La taille minimum du titre est de 4 caracteres";
        }
        if(!key_exists(self::CONTENT_REF,$this->data) || $this->data[self::CONTENT_REF] === "" ){
            $this->error[self::CONTENT_REF] ="Le champ est vide";
        }
        else if(mb_strlen($this->data[self::CONTENT_REF], 'UTF8') > 2500){
            $this->error[self::CONTENT_REF] = "La taille maximum de la description est de 2500 caracteres";
        }
        else if(mb_strlen($this->data[self::CONTENT_REF], 'UTF8') < 4){
            $this->error[self::CONTENT_REF] = "La taille minimum de la description est de 4 caracteres";
        }
        if(!key_exists(self::ARTIST,$this->data) || $this->data[self::ARTIST] === "" ){
            $this->error[self::ARTIST] ="Un artiste est nécessaire";
        }
        else if(mb_strlen($this->data[self::ARTIST], 'UTF8') > 35){
            $this->error[self::ARTIST] = "La nom de l'artiste est de 35 caractères maximum";
        }
        if (!empty($this->error)) {
            return false;
        }

        // Si titre et contenu vérifiés, vérification du fichier envoyé
        //Si files reçu, alors on vérifie le fichier
        //Sinon on valide le forumalire si data[files] existe
        if ($this->files != []) {
            if ($nom = $this->validFile()) {
                $this->data[self::IMG_REF] = $nom;
            } else {
                return false;
            }
        } else {
            if (!key_exists(self::IMG_REF, $this->data)) {
                return false;
            }
        }
        return true;        
    }

    public function getErrorId($name)
    {
        if ($this->error && isset($this->error[$name])) {
            return $this->error[$name];
        }
        return null;
    }

    public function getDataId($name)
    {
        if (isset($this->data) && isset($this->data[$name])) {
            return $this->data[$name];
        }
        return null;
    }

    public function setCreationUser($userName)
    {
        $this->data[self::USER] = $userName;
    }

    public function setMainPicture($getMainPicture)
    {
        $this->data[self::IMG_REF] = $getMainPicture;
    }

    public function validFile()
    {
        //Si aucun fichier reçu
        if (empty($this->files)) {
            $this->error[self::IMG_REF] = 'Pas de fichier reçu';
            return false;
        }

        //Si erreur de transmission
        if ($this->files[self::IMG_REF]['error']) {
            switch ($this->files[self::IMG_REF]['error']) {
                case UPLOAD_ERR_INI_SIZE:
                    $this->error[self::IMG_REF] = 'Taille maximum dépassée';
                    break;
                case UPLOAD_ERR_PARTIAL:
                    $this->error[self::IMG_REF] = 'Erreur, fichier partiellement envoyé';
                    break;
                case UPLOAD_ERR_NO_FILE:
                    $this->error[self::IMG_REF] = 'Pas de fichier envoyé';
                    break;
            }
            return false;
        }

        $max_size = 2000000;
        $extension = pathinfo($this->files[self::IMG_REF]['name'])['extension'];
        $prefix = uniqid('upload_') . '.' . $extension;
        $destination = UPLOAD_DIR . $prefix;
        $nomFichier = $this->files[self::IMG_REF]["name"];
        $taille = $this->files[self::IMG_REF]['size'];

        // Vérification d'il s'agit bien d'un image en regardant la signature du fichier
        if (!exif_imagetype($_FILES[self::IMG_REF]['tmp_name'])) {
            $this->error[self::IMG_REF] = 'Impossible d\'utiliser ce fichier, ne n\'est pas une image';
            return false;
        }

        // Déplacement du fichier dans le dossier UPLOAD et retourne le nom créé
        if ($taille > $max_size) {
            $this->error[self::IMG_REF] = "Le fichier dépasse la taille autorisée";
        } elseif ($nomFichier != '') {
            if (is_uploaded_file($this->files[self::IMG_REF]["tmp_name"])) {
                if (move_uploaded_file($this->files[self::IMG_REF]['tmp_name'], $destination)) {
                    return $prefix;
                } else {
                    $this->error[self::IMG_REF] = 'Une erreur s\'est produite pendant la copie du fichier';
                }
            } else {
                $this->error[self::IMG_REF] = 'Le fichier n\'à pas été envoyé';
            }
        } else {
            $this->error[self::IMG_REF] = 'Une erreur est survenue';
        }
        return false;
    }
}