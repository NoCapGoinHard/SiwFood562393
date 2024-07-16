/*/*popolamento cuochi*/
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
*/




/*NUOVO DATABASE PER MOSTRARLO ALL'ESAME*/


/*CUOCHI*/

INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Massimo', 'Bottura', '30-09-1962');
INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Nadia', 'Santini', '19-12-1953');
INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Antonino', 'Cannavacciuolo', '16-04-1975');
INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Carlo', 'Cracco', '08-10-1965');
INSERT INTO Cuoco (id, nome, cognome, data_nascita) VALUES (nextval('cuoco_seq'), 'Luisa', 'Marelli Valazza', '20-12-1950');


/*RICETTE*/
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Pasta al pesto di menta e briciole di pane', 'Deliziosa variante del tipico piatto italiano', (SELECT id FROM Cuoco WHERE nome = 'Massimo' AND cognome = 'Bottura'));
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Passatelli di nonna Ancella', 'I passatelli di nonna Ancella sono la ricetta con cui lo chef Massimo Bottura prepara questa specialità emiliana a base di pangrattato, uova e parmigiano', (SELECT id FROM Cuoco WHERE nome = 'Massimo' AND cognome = 'Bottura'));

INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Tortelli di zucca con burro e parmigiano', 'I tortelli di zucca, un classico della cucina di pianura, si trovano da Ferrara a Cremona, e sono oggetto di sfide culinarie destinate a lasciare ogni cuoco certi di aver fatto i migliori tortelli del mondo!', (SELECT id FROM Cuoco WHERE nome = 'Nadia' AND cognome = 'Santini'));
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Composta di Pomodori, Melanzane, Basilico e Olio extra Vergine d’Oliva del lago di Garda', 'Ricetta di Nadia Santini', (SELECT id FROM Cuoco WHERE nome = 'Nadia' AND cognome = 'Santini'));

INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Carne cruda con salsa al parmigiano e tuorlo d’uovo', 'Piatto gourmet tra i più amati', (SELECT id FROM Cuoco WHERE nome = 'Antonino' AND cognome = 'Cannavacciuolo'));
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Scorfano in umido', 'Uno dei migliori secondi piatti del repertorio', (SELECT id FROM Cuoco WHERE nome = 'Antonino' AND cognome = 'Cannavacciuolo'));

INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Sfoglie di polenta croccante', 'Specialità del nord Italia rivisitata in chiave gourmet', (SELECT id FROM Cuoco WHERE nome = 'Carlo' AND cognome = 'Cracco'));
INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Rombo in crosta di cacao', 'Semplice e veloce da realizzare, ma con effetti speciali garantiti', (SELECT id FROM Cuoco WHERE nome = 'Carlo' AND cognome = 'Cracco'));

INSERT INTO Ricetta (id, nome, descrizione, cuoco_id) VALUES (nextval('ricetta_seq'), 'Cannoli di polenta, cardi e tartufo', 'Hai mai pensato che i cardi potessero avere un discreto impatto in una ricetta? Questa specialità te lo dimostrerà', (SELECT id FROM Cuoco WHERE nome = 'Luisa' AND cognome = 'Marelli Valazza'));


/*////////////////////INGREDIENTI*/
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Basilico', '200g', (SELECT id FROM Ricetta WHERE nome = 'Pasta al pesto di menta e briciole di pane'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Menta', '120g', (SELECT id FROM Ricetta WHERE nome = 'Pasta al pesto di menta e briciole di pane'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Prezzemolo', '50g', (SELECT id FROM Ricetta WHERE nome = 'Pasta al pesto di menta e briciole di pane'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Fusilli', '600g', (SELECT id FROM Ricetta WHERE nome = 'Pasta al pesto di menta e briciole di pane'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Brodo di pollo', '1L', (SELECT id FROM Ricetta WHERE nome = 'Passatelli di nonna Ancella'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Pangrattato', '150g', (SELECT id FROM Ricetta WHERE nome = 'Passatelli di nonna Ancella'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Uova', '3', (SELECT id FROM Ricetta WHERE nome = 'Passatelli di nonna Ancella'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Limone', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Passatelli di nonna Ancella'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Passatelli di nonna Ancella'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Zucca', '4/3 di tazza', (SELECT id FROM Ricetta WHERE nome = 'Tortelli di zucca con burro e parmigiano'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Burro', '1/4 di tazza', (SELECT id FROM Ricetta WHERE nome = 'Tortelli di zucca con burro e parmigiano'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Parmigiano grattuggiato', '1 cucchiaio colmo', (SELECT id FROM Ricetta WHERE nome = 'Tortelli di zucca con burro e parmigiano'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Tortelli di zucca con burro e parmigiano'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Pomodori', '8', (SELECT id FROM Ricetta WHERE nome = 'Composta di Pomodori, Melanzane, Basilico e Olio extra Vergine d’Oliva del lago di Garda'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Melanzana', '1 a persona', (SELECT id FROM Ricetta WHERE nome = 'Composta di Pomodori, Melanzane, Basilico e Olio extra Vergine d’Oliva del lago di Garda'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Olio EV', '50ml', (SELECT id FROM Ricetta WHERE nome = 'Composta di Pomodori, Melanzane, Basilico e Olio extra Vergine d’Oliva del lago di Garda'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Composta di Pomodori, Melanzane, Basilico e Olio extra Vergine d’Oliva del lago di Garda'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Scamone di Fassona piemontese', '400g', (SELECT id FROM Ricetta WHERE nome = 'Carne cruda con salsa al parmigiano e tuorlo d’uovo'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Panna fresca', '200ml', (SELECT id FROM Ricetta WHERE nome = 'Carne cruda con salsa al parmigiano e tuorlo d’uovo'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Tuorli', '2 a persona', (SELECT id FROM Ricetta WHERE nome = 'Carne cruda con salsa al parmigiano e tuorlo d’uovo'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Carne cruda con salsa al parmigiano e tuorlo d’uovo'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Scorfano', '1', (SELECT id FROM Ricetta WHERE nome = 'Scorfano in umido'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Pomodori datterini', '400g', (SELECT id FROM Ricetta WHERE nome = 'Scorfano in umido'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Cipolla', '1', (SELECT id FROM Ricetta WHERE nome = 'Scorfano in umido'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Spicchi di aglio', '2', (SELECT id FROM Ricetta WHERE nome = 'Scorfano in umido'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Farina di mais giallo per polenta rapida', '190g', (SELECT id FROM Ricetta WHERE nome = 'Sfoglie di polenta croccante'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Olio EV', '15g a persona', (SELECT id FROM Ricetta WHERE nome = 'Sfoglie di polenta croccante'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Sfoglie di polenta croccante'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Piccolo rombo eviscerato', '800g bastano', (SELECT id FROM Ricetta WHERE nome = 'Rombo in crosta di cacao'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Grue di cacao', '300g', (SELECT id FROM Ricetta WHERE nome = 'Rombo in crosta di cacao'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale grosso', '125g', (SELECT id FROM Ricetta WHERE nome = 'Rombo in crosta di cacao'));


INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Farina di polenta macinata molto fine', '200g', (SELECT id FROM Ricetta WHERE nome = 'Cannoli di polenta, cardi e tartufo'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Acqua', '1/2 L', (SELECT id FROM Ricetta WHERE nome = 'Cannoli di polenta, cardi e tartufo'));
INSERT INTO Ingrediente (id, nome, quantita, ricetta_Id) VALUES (nextval('ingrediente_seq'), 'Sale', 'Q.B.', (SELECT id FROM Ricetta WHERE nome = 'Cannoli di polenta, cardi e tartufo'));








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














