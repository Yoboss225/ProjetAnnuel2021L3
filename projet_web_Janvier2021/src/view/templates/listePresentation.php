<?php
/**
 * @var array $tableaux
 * @var Tableau $tableau
 * @var App\src\view\View $this
 */

use App\src\model\Tableau\Tableau;

?>
<div class="article-content">
    <?php
    foreach ($tableaux as $id => $tableau):
        ?>
        <div class="item">
            <div class="img-wrapper">
                <a href="<?= $this->router->getPresentationTableauURL($id) ?>"><img src="./upload/<?= $tableau->getMainPicture()?>" alt=""></a>
            </div>
            <div class="details">
                <div class="details-date">
                    <time><?= $this->htmlesc($tableau->getCreatedAt()) ?></time>
                </div>
                <h2 class="details-title"><a href="<?= $this->router->getPresentationTableauURL($id) ?>"><?= $this->htmlesc($tableau->getTitle())?></a></h2>
                <div class="details-more">
                    <a href="<?= $this->router->getPresentationTableauURL($id) ?>">Lire plus</a>
                </div>
            </div>
        </div>
    <?php endforeach; ?>
</div>