<div class="single-wrap">
    <div class="single-title">
        <div class="single-title-item">
            <h1>Technologie web, projet mini-site</h1>
            <h2>Page à propos</h2>
        </div>
    </div>
    <div class="single-content">
        <div class="single-box">
            <div class="groupe">
                <h4>Groupe 42</h4>
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">NUMETU</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Prénom</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>21809848</td>
                            <td>LEFEVRE</td>
                            <td>Alex</td>
                        </tr>
                        <tr>
                            <td>21805304</td>
                            <td>MALBEC</td>
                            <td>Elie</td>
                        </tr>
                        <tr>
                            <td>21811619</td>
                            <td>KABLAN</td>
                            <td>Miezan</td>
                        </tr>
                        <tr>
                            <td>21810777</td>
                            <td>KACHRI</td>
                            <td>Oussama</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="realisations">
                <h4>Liste des compléments réalisés</h4>
                <ul class="itemListe">
                    <li>
                        <p><span>Un objet peut être illustré par une image (et une seule, non modifiable) uploadée par le créateur de l'objet</span>Une image est uploadé par le créateur de l'objet lors de sa création. Nous avons mis en place un système de vérification stricte du type de fichier reçu. Nous utilisons pour cela la fonction <code>exif_imagetype</code> de PHP, cependant elle peut apporter de faux positifs sur certains fichiers.</p>
                    </li>
                    <li>
                        <p><span>Dans le formulaire d'inscription, vérification en temps réel de la disponibilité du login</span>Dans notre cas, la recherche de disponibilité s'effectue uniquement après la taille minimum requise par l'attribut <code>minlenght</code> de l'<code>input</code> (ici 4) et ensuite à chaque lettre tapée. Cette technique utilise une requête Ajax avec le script <code>dispoPseudo.js</code>. Le type de réponse est du texte brut.</p>
                    </li>
                    <li>
                        <p><span>Site responsive</span>Le site est adapté à une visualisation sur mobiles, les medias queries nous permettent d'adapter le style de mise en page par rapport à la taille de l'écran. Des améliorations pourraient être portées sur la responsivité du menu.</p>
                    </li>
                </ul>
            </div>
            <div class="repartition">
                <h4>Répartition des tâches</h4>
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <th scope="col">Yoann</th>
                            <th scope="col">Elie</th>
                            <th scope="col">Oussama</th>
                            <th scope="col">Alex</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">Création autoloader</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Style CSS et JS</th>
                            <td>x</td>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Scripts création table SQL</th>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Validation des données</th>
                            <td>x</td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Partie modèle tableau</th>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Partie modèle des comptes</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Création d'un objet</th>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Suppression d'un objet</th>
                            <td></td>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Connexion et création de compte</th>
                            <td></td>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Création des méthodes de stockage</th>
                            <td></td>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Création des templates d'affichage</th>
                            <td>x</td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Mise à jour du routeur et gestion des routes</th>
                            <td>x</td>
                            <td></td>
                            <td>x</td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Disponibilité en temps réel du login</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        <tr>
                            <th scope="row">Scripts Javascript</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>x</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="choix">
                <h4>Choix en matière de design, modélisation, structure de code</h4>
                <p>L'architecture des dossiers de l'application est composée de 2 dossiers principaux, <code>public</code> et <code>src</code>. Le premier contient ce qui est accessible par l'utilisateur final, c'est à dire le point d'entrée de l'application : <code>index.php</code> ainsi que les ressources nécessaires comme les images et les styles sont dans ce dossier.</p>
                <p>Le dossier <code>src</code> contient le fonctionnement interne sur le serveur de l'application. Non accessible par l'utilisateur final pour des raisons de sécurité. Ce dossier contient la logique métier, les modèles, templates et vues qui permettent de créer la page demandée par l'utilisateur.</p>
                <p>Le dossier <code>src</code> est composé de trois parties qui représentent les trois pôles d'une architecture MVC : <code>model</code> contenant la représentation des élements de l'application, <code>controller</code> qui effectue les actions demandées par l'utilisateur sur le modèle et enfin la partie <code>view</code> pour représenter le modèle dans l'état actuel.</p>
                <p>Dans le dossier <code>model</code>, les dossiers <code>Account</code> et <code>Tableau</code> correspondent aux parties modèles respectives et comptes utilisateurs et des objets tableaux.</p>
                <p>Quand au dossier <code>DAO</code>, il contient les interfaces génériques implémentées par les méthodes de stockage de données des tableaux et des utilisateurs. Ce dossier pourrait être réutilisé sur d'autre projets.</p>
                <p>Avec le structure dont nous disposons, un fichier <code>.htaccess</code> à été mis en place pour indiquer utiliser l'url rewriting et considérer le dossier <code>public</code> comme root directory.</p>
            </div>
            <div class="autre">
                <h4>Autres points</h4>
                <p>Les conditions actuelles de travail nous ont poussés à travailler ensemble en visioconférence, ainsi le travail s'est partagé entre nous en visioconférence, les statistiques ne reflètent donc pas l'engagement de chacun d'entre nous.</p>
                <p>Un utilisateur avec pour pseudo <code>admin</code> et mot de passe <code>toto</code> peut modifier et supprimer n'importe quel objet du site, nous n'avons pas mis en place d'espace d'administration mais ajouté ce rôle plus permissif.</p>
                <p>La persistance du formulaire de création de compte n'est valable que pour un seul rechargement de page, cela permet d'éviter d'avoir des conflits si plusieurs onglets sont ouverts. De plus, cela évite d'avoir un formulaire pré-rempli si la personne à quitté la page puis y revient. Il n'y a pas de persistante sur le formulaire de connexion, si les informations sont incorrects, il faut retaper le login et le mot de passe.</p>
                <p>Les formulaire de création et modification des objets sont persistants.</p>
                <p>Pour ce projet, nous nous sommes servis du gestionnaire de version GIT. L'utilisation de branches nous as permis d'ajouter des fonctionnalités sans perturber le code des autres membres.</p>
            </div>
        </div>
    </div>
</div>
