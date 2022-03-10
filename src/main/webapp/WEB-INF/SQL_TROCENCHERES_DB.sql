USE TROCENCHERES_DB

CREATE TABLE CATEGORIES (
    no_categorie			INTEGER IDENTITY(1,1) NOT NULL,
    libelle					VARCHAR(30) NOT NULL,
	CONSTRAINT categorie_pk PRIMARY KEY (no_categorie)
)

CREATE TABLE RETRAITS (
	no_article				INTEGER NOT NULL,
    rue						VARCHAR(30) NOT NULL,
    code_postal				VARCHAR(15) NOT NULL,
    ville					VARCHAR(30) NOT NULL,
	CONSTRAINT retrait_pk PRIMARY KEY  (no_article)
)

CREATE TABLE ENCHERES (
	no_enchere				INTEGER	IDENTITY(1,1) NOT NULL,
    no_utilisateur			INTEGER NOT NULL,
    no_article				INTEGER NOT NULL,
    date_enchere			DATETIME NOT NULL,
	montant_enchere			INTEGER NOT NULL,
	CONSTRAINT enchere_pk PRIMARY KEY (no_enchere)
)

CREATE TABLE UTILISATEURS (
    no_utilisateur			INTEGER IDENTITY(1,1) NOT NULL,
    pseudo					VARCHAR(30) NOT NULL,
    nom						VARCHAR(30) NOT NULL,
    prenom					VARCHAR(30) NOT NULL,
    email					VARCHAR(40) NOT NULL,
    telephone				VARCHAR(15),
    rue						VARCHAR(30) NOT NULL,
    code_postal				VARCHAR(10) NOT NULL,
    ville					VARCHAR(30) NOT NULL,
    mot_de_passe			VARCHAR(64) NOT NULL,
    credit					INTEGER NOT NULL,
    administrateur			BIT NOT NULL,
	utilisateur_desactive	BIT NOT NULL,
	utilisateur_supprime	BIT NOT NULL,
	CONSTRAINT utilisateur_pk PRIMARY KEY (no_utilisateur),
	CONSTRAINT utilisateur_pseudo_un UNIQUE (pseudo),
	CONSTRAINT utilisateur_email_un UNIQUE (email)
)

CREATE TABLE ARTICLES_VENDUS (
    no_article				INTEGER IDENTITY(1,1) NOT NULL,
    nom_article				VARCHAR(30) NOT NULL,
    description_article		VARCHAR(300) NOT NULL,
	date_debut_encheres		DATETIME NOT NULL,
    date_fin_encheres		DATETIME NOT NULL,
    prix_initial			INTEGER NOT NULL,
    prix_vente				INTEGER,
	vente_active			BIT NOT NULL,
    no_utilisateur			INTEGER NOT NULL,
    no_categorie			INTEGER NOT NULL,
	article_supprime		BIT NOT NULL,
	photo_article			VARBINARY(MAX),
	CONSTRAINT articles_vendus_pk PRIMARY KEY (no_article)
)

--CREATION DES CONTRAINTES D'INTEGRITE REFERENTIELLES DE CLES ETRANGERES
ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION;
	
ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY (no_categorie) REFERENCES categories (no_categorie)
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY (no_utilisateur) REFERENCES utilisateurs (no_utilisateur)
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION;


--INSERTION CATEGORIES
INSERT INTO CATEGORIES(libelle) VALUES('Informatique');
INSERT INTO CATEGORIES(libelle) VALUES('Ameublement');
INSERT INTO CATEGORIES(libelle) VALUES('Vêtement');
INSERT INTO CATEGORIES(libelle) VALUES('Sport&Loisirs');



--DELETE FROM ARTICLES_VENDUS WHERE no_article='7';

--SUPRESSION DES TABLES
--DROP TABLE ENCHERES, RETRAITS, ARTICLES_VENDUS, CATEGORIES, UTILISATEURS;

--SELECT * FROM articles_vendus INNER JOIN retraits ON articles_vendus.no_article = retraits.no_article INNER JOIN utilisateurs ON articles_vendus.no_utilisateur = utilisateurs.no_utilisateur WHERE articles_vendus.no_article=2;
--UPDATE UTILISATEURS SET credit=500;

--DROP TABLE ENCHERES, RETRAITS, ARTICLES_VENDUS, CATEGORIES, UTILISATEURS;