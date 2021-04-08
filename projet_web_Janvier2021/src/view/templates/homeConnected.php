<?php
/**
 * @var \App\src\model\Account\Account $account;
 * @var \App\src\view\View $this
 */
?>
<div class='single-wrap'>
    <div class="single-box">
    <h2>Bonjour <?= $account->getLogin()?></h2>
    <p>Bienvenue sur le site présentation des tableaux d'arts, vous pouvez dès à présent visualiser ceux disponibles sur la <a href="<?= $this->router->getListeURL()?>">liste des oeuvres disponibles</a>.</p>
    <p>Vous pouvez également <a href="<?= $this->router->getCreationTableauURL(); ?>">créer vos oeuvres.</a></p>
    <p>La page <a href="<?= $this->router->getAProposURL(); ?>">à propos</a> rassemble les informations sur le projet disponibles hors connexion.</p>
    <p>Votre compte à été crée le <time><?= $account->getCreatedAt()?></time></p>
    </div>
</div>
