INSERT INTO facultades (nombre) VALUES ('FBI');
INSERT INTO facultades (nombre) VALUES ('Sindicate');



INSERT INTO departamentos (nombre, facultad_id) VALUES ('X-Files', 1);
INSERT INTO departamentos (nombre, facultad_id) VALUES ('US Sindicate', 2);

INSERT INTO tipos_solicitud (nombre) VALUES ('comision');

INSERT INTO roles (nombre) VALUES ('agente especial');


INSERT INTO usuarios (nombre, apellido, email, departamento_id, rol_id) VALUES ('Dana', 'Scully', '@queequeg', 1, 1);
INSERT INTO usuarios (nombre, apellido, email, departamento_id, rol_id) VALUES ('Fox', 'Mulder', '@trustno1', 1, 1);
INSERT INTO usuarios (tipo_identificacion, nombre, apellido, email, departamento_id) VALUES ('CC', 'Alex', 'Krycek', '@', 2);


INSERT INTO comisiones (lugar, usuario_id) VALUES ('Tunguska', 2);
INSERT INTO comisiones (lugar, usuario_id) VALUES ('Cassiopea', 1);
INSERT INTO comisiones (lugar, usuario_id, tipo_solicitud_id) VALUES ('Rusia', 3, 1);


INSERT INTO cumplidos (nombre, informacion_complementaria, comision_id) VALUES ('black oil', 'purity control', 1);

INSERT INTO documentos (nombre, comision_id) VALUES ('xfile', 2);










