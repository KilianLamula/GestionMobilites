INSERT INTO ETUDIANT (nom, num_etud, prenom, promo) VALUES
    ( 'GAUTHIER',4,'Lise', 2024),
    ('MATTON',3,'Hugo',2024),
    ('BEN',2,'Rayane',2024),
    ('LAMULA',1,'Kilian',2024),
    ('VILLEDIEU',5,'Baptiste',2023),
    ('GAY',6,'Colin',2023);



INSERT INTO DESTINATION (date_fin_de_contrat_isis,image,nb_place_annee,nb_place_semestre,nom_etablissement_accueil,type_mobilite,ville,pays) VALUES
    ('2025-02-02', '', 4, 2, 'Artic University', 1,'Tromso','Norvege'),
    ('2022-09-02', '', 4, 2, 'European University Of Cyprus', 1,'Nicosia','Chypre');

INSERT INTO MOBILITE (date_depart, duree_en_mois, periode, destination_id, etudiant_id) VALUES
    ('2023-04-10', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'European University Of Cyprus', SELECT ID FROM ETUDIANT WHERE nom = 'BEN' ),
    ('2022-04-10', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'European University Of Cyprus', SELECT ID FROM ETUDIANT WHERE nom = 'MATTON' ),
    ('2019-04-10', 5, 1, SELECT ID FROM DESTINATION WHERE nom_etablissement_accueil = 'Artic University', SELECT ID FROM ETUDIANT WHERE nom = 'VILLEDIEU' );

/*
INSERT INTO DOCUMENT (description, fichier, intitule) VALUES
    ('Meilleur fichier ever', 'fichier','Le fichier a valider avec validation');
*/


