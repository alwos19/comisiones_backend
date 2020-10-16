INSERT INTO facultades (nombre) VALUES ('FBI');
INSERT INTO facultades (nombre) VALUES ('Sindicate');


INSERT INTO departamentos (nombre, facultad_id) VALUES ('X-Files', 1);
INSERT INTO departamentos (nombre, facultad_id) VALUES ('US Sindicate', 2);


INSERT INTO usuarios (nombre, apellido, email, departamento_id) VALUES ('Dana', 'Scully', '@queequeg', 1);
INSERT INTO usuarios (nombre, apellido, email, departamento_id) VALUES ('Fox', 'Mulder', '@trustno1', 1);
INSERT INTO usuarios (tipo_identificacion, nombre, apellido, email, departamento_id) VALUES ('CC', 'Alex', 'Krycek', '@', 2);

INSERT INTO comisiones (lugar, usuario_id) VALUES ('Tunguska', 3);
INSERT INTO comisiones (lugar, usuario_id) VALUES ('USA', 3);

