<?php
/**
 * @var \App\src\view\View|\App\src\view\PrivateView $this
 */
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css2?family=Cabin:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <title><?= $this->title ?></title>
</head>
<body>
<div class="wrapper">
    <header id="header">
        <div class="header-wrap">
            <div class="header-content">
                <div class="header-title">
                    <a href="<?= $this->router->getHomeURL() ?>" id="site-title"><?= $this->title ?></a>
                </div>
                <div class="header-nav">
                    <nav>
                        <ul>
                            <?php
                            /* Construit le menu à partir d'un tableau associatif texte=>lien. */
                            foreach ($this->menu as $text => $a) {
                                echo "<li><a href=\"$a\">$text</a></li>";
                            }
                            ?>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <main>
        <section class="first-section" id="first-section">
            <?php if ($this->feedback !== '') : ?>
                <div class="alert">
                    <span class="closebtn"><?= $this->feedback; ?></span>
                </div>
            <?php endif;?>
            <article>
                <?= $this->content ?>
            </article>
        </section>
    </main>
    <footer id="footer">
        <div class="footer-content">
            <p>Technologie Web, semestre 5, Université de Caen Normandie</p>
        </div>
    </footer>
</div>
    <div>
        <a href="#" id="cRetour" class="cInvisible"></a>
    </div>
<script src="js/script.js"></script>
</body>
</html>