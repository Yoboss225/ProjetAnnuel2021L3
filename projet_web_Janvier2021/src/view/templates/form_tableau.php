<?php
/**
 * @var TableauBuilder $tableau
 * @var string $link
 * @var string $submit
 * @var boolean $uploadFile
 * @var string $title
 * @var \App\src\view\View $this
 */

use App\src\model\Tableau\TableauBuilder;

?>
<div class="single-wrap">
    <div class="single-title">
        <div class="single-title-item">
            <h1><?= $title ?></h1>
        </div>
    </div>
    <div class="single-content">
        <div class="single-box">
            <form action="<?= $link ?>" method="post" <?php if ($uploadFile) {echo 'enctype="multipart/form-data"';}?> >
                <label for="<?= TableauBuilder::TITLE_REF ?>">Titre du tableau
                    <input type="text" name="<?= TableauBuilder::TITLE_REF ?>" id="<?= TableauBuilder::TITLE_REF ?>" required="required"
                           value="<?= $this->htmlesc($tableau->getDataId(TableauBuilder::TITLE_REF)); ?>" autofocus>
                    <span class="error"><?= $tableau->getErrorId(TableauBuilder::TITLE_REF) ?></span>
                </label>
                <label for="<?= TableauBuilder::ARTIST ?>">Artiste
                    <input type="text" name="<?= TableauBuilder::ARTIST ?>" id="<?= TableauBuilder::ARTIST ?>" required="required"
                           value="<?= $this->htmlesc($tableau->getDataId(TableauBuilder::ARTIST)); ?>">
                    <span class="error"><?= $tableau->getErrorId(TableauBuilder::ARTIST) ?></span>
                </label>
                <label for="<?= TableauBuilder::CONTENT_REF ?>">Description
                    <textarea name="<?= TableauBuilder::CONTENT_REF ?>" id="<?= TableauBuilder::CONTENT_REF ?>"
                              cols="30" rows="10" lang="fr"
                              required="required"><?= $this->htmlesc($tableau->getDataId(TableauBuilder::CONTENT_REF)); ?></textarea>
                    <span class="error"><?= $tableau->getErrorId(TableauBuilder::CONTENT_REF) ?></span>
                </label>
                <?php if ($uploadFile) :?>
                <label for="<?= TableauBuilder::IMG_REF?>">Sélection du fichier (2Mo maximum)
                    <input type="file" name="<?= TableauBuilder::IMG_REF?>" id="<?= TableauBuilder::IMG_REF?>">
                    <span class="error"><?= $tableau->getErrorId(TableauBuilder::IMG_REF) ?></span>
                </label>
                <?php endif;?>
                <div class="actions">
                    <button type="submit"><?= $submit ?></button>
                </div>
            </form>
        </div>
    </div>
</div>