<?php
use App\src\model\Account\AccountBuilder;

/**
 * @var $method string
 * @var $link string
 * @var $validate string
 * @var $phrase string
 * @var $user AccountBuilder
 */
?>
<div class="single-wrap">
    <div class="single-title">
        <div class="single-title-item">
            <h1><?= $method ?></h1>
        </div>
    </div>
    <div class="single-content">
        <div class="single-box">
            <form action="<?= $link ?>" method="post">
                <label for="<?= AccountBuilder::PSEUDO_REF ?>">Pseudo
                    <input type="text" name="<?= AccountBuilder::PSEUDO_REF ?>" id="<?= AccountBuilder::PSEUDO_REF ?>" value="<?= $this->htmlesc($user->getDataId(AccountBuilder::PSEUDO_REF)); ?>" autofocus minlength="<?= AccountBuilder::MIN_LENGTH_LOGIN ?>" maxlength="<?= AccountBuilder::MAX_LENGTH_LOGIN?>">
                    <span class="error"><?= $user->getErrorId(AccountBuilder::PSEUDO_REF) ?></span>
                    <span id="exist"></span>
                </label>
                <label for="<?= AccountBuilder::PASSWORD_REF ?>">Mot de passe
                    <input type="password" name="<?= AccountBuilder::PASSWORD_REF ?>" minlength="<?= AccountBuilder::MIN_LENGTH_PASSWORD ?>" maxlength="<?= AccountBuilder::MAX_LENGTH_PASSWORD?>"
                           id="<?= AccountBuilder::PASSWORD_REF ?>"
                           value="<?= $this->htmlesc($user->getDataId(AccountBuilder::PASSWORD_REF)); ?>">
                    <span class="error"><?= $user->getErrorId(AccountBuilder::PASSWORD_REF) ?></span>
                </label>
                <div class="actions">
                    <button type="submit"><?= $validate ?></button>
                </div>
            </form>
            <?= $phrase ?>
        </div>
    </div>
</div>
<?php if (isset($dispoPseudoJS)) :?>
<script src="js/dispoPseudo.js"></script>
<?php endif;?>
