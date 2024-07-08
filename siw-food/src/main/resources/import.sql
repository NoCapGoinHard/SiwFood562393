/*popolamento cuochi*/
INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Cuoco1', 'Cognome1', '19-12-2001');
INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Cuoco2', 'Cognome2', '19-12-2002');


/*popolamento ricette*/
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Torta', 'Torta ai carboni passivi', (SELECT id FROM Cuoco WHERE nome = 'Cuoco1' AND cognome = 'Cognome1'));
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Pane', 'Pane ai carboni passivi', (SELECT id FROM Cuoco WHERE nome = 'Cuoco2' AND cognome = 'Cognome2'));


/*////////////////////popolamento ingredienti*/
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Uova', '4', (SELECT id FROM Ricetta WHERE nome = 'Torta'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Zucchero', '150g', (SELECT id FROM Ricetta WHERE nome = 'Torta'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Lievito', '200g', (SELECT id FROM Ricetta WHERE nome = 'Torta'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Farina', '200g', (SELECT id FROM Ricetta WHERE nome = 'Pane'));
/*////////////////////popolamento allergeni*/
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Ovoalbumina');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Ovomucoide');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Tracce di soia');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Senape');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Frutta a guscio');
INSERT INTO Allergene (id, nome) VALUES (nextval('allergene_seq'), 'Glutine');
/*/////////////////////popolamento join-column ingrediente<->allergene*/
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Uova'), (SELECT id FROM Allergene WHERE nome = 'Ovoalbumina'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Uova'), (SELECT id FROM Allergene WHERE nome = 'Ovomucoide'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Zucchero'), (SELECT id FROM Allergene WHERE nome = 'Tracce di soia'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Zucchero'), (SELECT id FROM Allergene WHERE nome = 'Senape'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Zucchero'), (SELECT id FROM Allergene WHERE nome = 'Frutta a guscio'));
INSERT INTO ingrediente_allergene (ingrediente_Id, allergene_Id) VALUES ((SELECT id FROM Ingrediente WHERE nome = 'Farina'), (SELECT id FROM Allergene WHERE nome = 'Glutine'));
