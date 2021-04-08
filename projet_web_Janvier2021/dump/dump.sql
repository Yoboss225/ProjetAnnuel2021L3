-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : jeu. 12 nov. 2020 à 14:21
-- Version du serveur :  8.0.22-0ubuntu0.20.04.2
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
-- --------------------------------------------------------

--
-- Structure de la table `comptes`
--

CREATE TABLE `comptes` (
  `id` int NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `createdAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `comptes`
--

INSERT INTO `comptes` (`id`, `login`, `password`, `role`, `createdAt`) VALUES
(1, 'vanier', '$2y$10$sCFIk691KbxfiwFVnKG1E.t/rLQaNNOQs6TO4bmWiSw0PnztHBmJW', 'user', '2020-11-12 14:18:37'),
(2, 'lecarpentier', '$2y$10$H8I.GvjMF9iUZx7nfwB6C.3LJl2T7I2j3LQG0HCMncLcdfK9yH2La', 'user', '2020-11-12 14:18:58'),
(3, 'admin', '$2y$10$wO1GepnvF9N8ylNIbUndVOLyJ2tCaQBC18YCFUhn.CguLduxYqVGu', 'admin', '2020-11-12 14:19:15');

-- --------------------------------------------------------

--
-- Structure de la table `tableaux`
--

CREATE TABLE `tableaux` (
  `id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `artist` varchar(35) DEFAULT NULL,
  `user_id` int NOT NULL,
  `main_picture` varchar(60) DEFAULT NULL,
  `createdAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comptes`
--
ALTER TABLE `comptes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Index pour la table `tableaux`
--
ALTER TABLE `tableaux`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userId_login` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `comptes`
--
ALTER TABLE `comptes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `tableaux`
--
ALTER TABLE `tableaux`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tableaux`
--
ALTER TABLE `tableaux`
  ADD CONSTRAINT `fk_userId_login` FOREIGN KEY (`user_id`) REFERENCES `comptes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
