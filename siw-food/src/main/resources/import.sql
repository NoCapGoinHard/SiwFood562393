insert into cuoco(id,nome,cognome,data_nascita,path_foto)
    values(
        nextval('cuoco_seq'),
        'cuoco1',
        'cognome1',
        '19/12/2001',
        '/images/cuocomatto.jpg'
    );
insert into cuoco(id,nome,cognome,data_nascita,path_foto)
    values(
        nextval('cuoco_seq'),
        'cuoco2',
        'cognome2',
        '19/12/2002',
        '/images/cuocomatto2.jpg'
    );


insert into ricetta(id,nome,descrizione,cuocoId,path_foto)
    values(
        nextval('ricetta_seq'),
        'ricetta1',
        'bona vera',
        select id from cuoco where nome = 'cuoco1',
        '/images/tortabruciata.jpg'
    )
insert into ricetta(id,nome,descrizione,cuocoId,path_foto)
    values(
        nextval('ricetta_seq'),
        'ricetta2',
        'ancora piu bona',
        select id from cuoco where nome = 'cuoco2',
        '/images/panebruciato.jpg'
    )  


insert into ingrediente(id,nome,quantita,ricettaId)
    values(
        nextval('ingrediente_seq'),
        'ingrediente1',
        '100',
        select id from ricetta where nome = 'ricetta1'
    );
insert into ingrediente(id,nome,quantita,ricettaId)
    values(
        nextval('ingrediente_seq'),
        'ingrediente2',
        '100',
        select id from ricetta where nome = 'ricetta1'
    );
insert into ingrediente(id,nome,quantita,ricettaId)
    values(
        nextval('ingrediente_seq'),
        'sale',
        '5',
        select id from ricetta where nome = 'ricetta1'
    );
insert into ingrediente(id,nome,quantita,ricettaId)
    values(
        nextval('ingrediente_seq'),
        'sale',
        '3',
        select id from ricetta where nome = 'ricetta2'
    );