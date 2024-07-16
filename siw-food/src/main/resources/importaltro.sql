

/*////////////////////popolamento allergeni*/
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Tracce di glutine');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Tracce di pesce');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Tracce di lattosio');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Tracce di frutta a guscio');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Solfiti');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Senape');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Soia');

INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Glutine');

INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Ovoalbumina');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Ovomucoide');

INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Nichel');

INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Lattosio');

INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Tracce di molluschi e/o crostacei');

/*/////////////////////popolamento join-column ingrediente<->allergene*/
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Tracce di glutine'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Tracce di pesce'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Tracce di lattosio'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Tracce di frutta a guscio'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Solfiti'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Senape'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Basilico'), (SELECT id FROM Allergene WHERE nome = 'Soia'));

INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Fusilli'), (SELECT id FROM Allergene WHERE nome = 'Glutine'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Semola di grano duro'), (SELECT id FROM Allergene WHERE nome = 'Glutine'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Sfoglia per lasagna'), (SELECT id FROM Allergene WHERE nome = 'Glutine'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Pangrattato'), (SELECT id FROM Allergene WHERE nome = 'Glutine'));

INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Uova'), (SELECT id FROM Allergene WHERE nome = 'Ovoalbumina'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Uova'), (SELECT id FROM Allergene WHERE nome = 'Ovomucoide'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Tuorli'), (SELECT id FROM Allergene WHERE nome = 'Ovomucoide'));

INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Pomodori'), (SELECT id FROM Allergene WHERE nome = 'Nichel'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Zucca'), (SELECT id FROM Allergene WHERE nome = 'Nichel'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Pomodori datterini'), (SELECT id FROM Allergene WHERE nome = 'Nichel'));

INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Burro'), (SELECT id FROM Allergene WHERE nome = 'Lattosio'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Parmigiano grattuggiato'), (SELECT id FROM Allergene WHERE nome = 'Lattosio'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Panna fresca'), (SELECT id FROM Allergene WHERE nome = 'Lattosio'));

INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Scorfano'), (SELECT id FROM Allergene WHERE nome = 'Tracce di molluschi e/o crostacei'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Piccolo rombo eviscerato'), (SELECT id FROM Allergene WHERE nome = 'Tracce di molluschi e/o crostacei'));

INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Grue di cacao'), (SELECT id FROM Allergene WHERE nome = 'Soia'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Grue di cacao'), (SELECT id FROM Allergene WHERE nome = 'Senape'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Grue di cacao'), (SELECT id FROM Allergene WHERE nome = 'Tracce di frutta a guscio'));
