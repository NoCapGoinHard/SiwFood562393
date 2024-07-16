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





/**/











