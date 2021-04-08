<?php


namespace App\src\model\Tableau;


class Tableau
{
    private $title;
    private $content;
    private $artist;
    private $user;
    private $main_picture;
    private $createdAt;

    public function __construct($title, $content, $artist, $user, $main_picture, $createdAt)
    {
        $this->title = $title;
        $this->content = $content;
        $this->user = $user;
        $this->artist = $artist;
        $this->main_picture = $main_picture;
        $this->createdAt = $createdAt;
    }

    public function getTitle()
    {
        return $this->title;
    }

    public function getContent()
    {
        return $this->content;
    }

    public function getCreationUser()
    {
        return $this->user;
    }

    public function getMainPicture()
    {
        return $this->main_picture;
    }

    public function getCreatedAt()
    {
        return $this->createdAt;
    }

    public function getArtist()
    {
        return $this->artist;
    }
}