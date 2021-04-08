<?php
/**
 * @var Tableau $tableau
 * @var \App\src\view\View $this
 * @var string $id
 * @var bool $actions
 */

use App\src\model\Tableau\Tableau;

?>
<div class="single-wrap">
    <div class="single-title">
        <div class="single-title-item">
            <h1><?= $this->htmlesc($tableau->getTitle()); ?></h1>
            <h4><?= $this->htmlesc($tableau->getArtist())?></h4>
        </div>
    </div>
    <div class="single-content">
        <div class="single-box">
            <figure class="single-img">
                <div class="single-img-box">
                    <img src="./upload/<?= $tableau->getMainPicture()?>" alt="">
                </div>
                <figcaption>
                    <p>Photo de <a href="#"><?= $this->htmlesc($tableau->getCreationUser()) ?></a></p>
                </figcaption>
            </figure>
        </div>
        <div class="single-box">
            <div class="single-details">
                <p><?= $this->htmlesc($tableau->getContent()); ?></p>
                <p>Posté le : <?= $this->htmlesc($tableau->getCreatedAt()); ?></p>
            </div>
            <?php if ($actions) :?>
            <h4>Actions</h4>
            <div class="actions">
                <a href="<?= $this->router->getUpdateTableauURL($id) ?>"><div class="button">Modifier</div></a>
                <a href="<?= $this->router->getDeleteItemURL($id) ?>"><div class="button">Supprimer</div></a>
            </div>
            <?php endif;?>
        </div>
    </div>
</div>