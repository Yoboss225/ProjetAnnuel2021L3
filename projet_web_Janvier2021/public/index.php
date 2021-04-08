<?php
/**
 * Devoir-projet : mini-site
 * Licence informatique, semestre 6
 *
 * MALBEC Elie  21805304
 * LEFEVRE Alex 21809848
 * KABlAN Miezan 21811619
 * KACHRI Oussama 21810777
 */

use App\src\model\Account\AccountStorageMySQL;
use App\src\controller\AuthenticationManager;
use App\src\model\Tableau\TableauStorageMySQL;


/**
 * Point d'entrée de l'application MVCR
 */

/**
 * Chargement de l'autoloder
 */
require_once '../src/Autoloader.php';
Autoloader::register();

/**
 * Constantes de connexion à la BD
 */

include '/users/21811619/private/mysql_config.php';

/**
 * Création de notre méthode d'accès aux données
 */
try {
    $bdd = new PDO(DSN, USER, PASSWORD);
    $bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (Exception $e) {
    die("Error to connect to database");
}

$tableauStorageMySQL = new TableauStorageMySQL($bdd);
$accountStorageMySQL = new AccountStorageMySQL($bdd);
$authenticationManager = new AuthenticationManager($accountStorageMySQL);

/**
 * Démarrage d'une session PHP
 */
session_name("tw3groupe42");
session_start();

/**
 * Démarrage de l'application
 */

$router = new App\src\Router();
$router->run($tableauStorageMySQL, $accountStorageMySQL, $authenticationManager);