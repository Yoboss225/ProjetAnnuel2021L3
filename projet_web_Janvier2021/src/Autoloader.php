<?php

/**
 * Class Autoloader, s'occupe de l'importation des classes quand elles sont nécessaires à un script.
 * Documentation sur l'auto-importation des classes :
 * https://www.php.net/manual/fr/language.oop5.autoload.php
 * @package App\src
 */
class Autoloader
{
    /**
     * Ajout d'un chargeur de classe automatique, qui se base sur la fonction statique autoload.
     * Cette fonction permet aux classes et interfaces d'être chargées si elles ne sont pas définies
     * actuellement. Le nom de la classe reçue est le namespace correspondant.
     */
    public static function register()
    {
        spl_autoload_register(array(__CLASS__, 'autoload'));
    }

    /**
     * Fonction permettant d'inclure le fichier demandé par spl_autoload_register. Le paramètre
     * $className est le namespace de la classe. Inclusion de la classe avec un require.
     * @param $className string Nom de la classe à inclure
     */
    public static function autoload($className)
    {
        // Namespace reçu de la forme App\src\controller\FrontController
        // Transformation en controller/FrontController.php pour inclusion
        $className = str_replace('App\\src\\', '', $className);
        $className = str_replace('\\', DIRECTORY_SEPARATOR, $className);
        require $className . ".php";
    }
}