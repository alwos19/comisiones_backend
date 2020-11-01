INSERT INTO facultades (nombre) VALUES ('FCEN');
INSERT INTO facultades (nombre) VALUES ('INGENIERIA');

INSERT INTO departamentos (nombre, facultad_id) VALUES ('Fisica', 1);
INSERT INTO departamentos (nombre, facultad_id) VALUES ('Biologia', 1);
INSERT INTO departamentos (nombre, facultad_id) VALUES ('Sistemas', 2);

INSERT INTO roles (nombre) VALUES ('profesor');
INSERT INTO roles (nombre) VALUES ('estudiante');

INSERT INTO tipos_solicitud (nombre) VALUES ('permiso');
INSERT INTO tipos_solicitud (nombre) VALUES ('comision');

INSERT INTO estados(nombre) VALUES ('En-Secretaria');
INSERT INTO estados(nombre) VALUES ('En-Decanato');


INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, estado, departamento_id, rol_id) VALUES ('CC', 100, 'Dana', 'Scully', 'fbi@queequeg.co', '123', 1, 1, 1);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, estado,  departamento_id, rol_id) VALUES ('CC',200, 'Fox', 'Mulder', 'fbi@trustno1.co', '123',1, 1, 1);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, estado,  departamento_id,  rol_id) VALUES ('CC',300, 'Alex', 'Krycek', 'fbi@sindicate.co', '123', 0, 2, 2);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, estado) VALUES ('CC', 400, 'Walter', 'Skinner', 'skinner@fbi.co', '123',1);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, estado) VALUES ('CC', 500, 'CGB', 'Spencer', 'watergate@fbi.co', '123', 2);

INSERT INTO comisiones (lugar, usuario_id) VALUES ('Tunguska', 2);
INSERT INTO comisiones (lugar, idioma, tipo_solicitud_id, usuario_id) VALUES ('US', 'ingles', 1, 1);
INSERT INTO comisiones (lugar, justificacion, idioma, usuario_id) VALUES ('Tunguska', 'astrobiologia','Ruso' , 3);
INSERT INTO comisiones (lugar, idioma, usuario_id) VALUES ('Inglaterra', 'ingles', 5);

INSERT INTO comisiones_estados(create_at, comision_id, estado_id) VALUES (NOW(), 4, 1);

INSERT INTO cumplidos (nombre, informacion_complementaria, comision_id) VALUES ('cumplido_1', 'purity_control', 1);

INSERT INTO documentos (nombre, comision_id) VALUES ('xfile', 1);








