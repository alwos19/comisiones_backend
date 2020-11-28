INSERT INTO facultades (nombre, centro_de_costo) VALUES ('FCEN', 1);
INSERT INTO facultades (nombre, centro_de_costo) VALUES ('INGENIERIA', 1);

INSERT INTO departamentos (nombre, facultad_id) VALUES ('Fisica', 1);
INSERT INTO departamentos (nombre, facultad_id) VALUES ('Biologia', 1);
INSERT INTO departamentos (nombre, facultad_id) VALUES ('Sistemas', 2);

INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_VICERRECTORIA');
INSERT INTO roles (nombre) VALUES ('ROLE_DECANO');
INSERT INTO roles (nombre) VALUES ('ROLE_DIRECTOR');
INSERT INTO roles (nombre) VALUES ('ROLE_SECRETARIA_DECANO');
INSERT INTO roles (nombre) VALUES ('ROLE_SECRETARIA_DIRECTOR');
INSERT INTO roles (nombre) VALUES ('ROLE_USUARIO');

INSERT INTO tipos_solicitud (nombre) VALUES ('permiso');
INSERT INTO tipos_solicitud (nombre) VALUES ('comision');

INSERT INTO estados(nombre) VALUES ('En-Secretaria');
INSERT INTO estados(nombre) VALUES ('En-Decanato');
INSERT INTO estados(nombre) VALUES ('Visto-bueno');
INSERT INTO estados(nombre) VALUES ('Devuelto');


INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, activo, create_at, departamento_id, rol_id) VALUES ('CC', 100, 'Dana', 'Scully', 'fbi@queequeg.co', '$2a$10$9eIlYCNsi.Nd7qEOHe5v9e/sX7W8s1rR5llrjtZEiFLfv2h58REFy', true, NOW(), 1, 1);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, activo, create_at,  departamento_id, rol_id) VALUES ('CC',200, 'Fox', 'Mulder', 'fbi@trustno1.co', '$2a$10$a8dlKiivsGJJCgZYvhPuX.rylr4vBNvg1sNpiDZeiby.GKHpSi3Ha', true, NOW(), 1, 1);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, activo, create_at,  departamento_id,  rol_id) VALUES ('CC',300, 'Alex', 'Krycek', 'fbi@sindicate.co', '$2a$10$VZeJK37uFRmufkc28VLuN.AZsLjoYOou3LLvb2mqZ/BtsCHZpqq0G', true,  NOW(), 2, 2);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, activo, create_at, username, password, enabled, rol_id) VALUES ('CC', 400, 'Walter', 'Skinner', 'skinner@fbi.co', ' ', true,  NOW(), 'Skinner', '$2a$10$5GZP9FOehonf9c.Dy1py7.MpSU.5ZQtn9L/NmOQhSWDbA2knnMSf2', true, 2);
INSERT INTO usuarios (tipo_identificacion, identificacion, nombre, apellido, email, contrasena, activo, create_at, username, password, enabled, rol_id) VALUES ('CC', 500, 'CGB', 'Spencer', 'watergate@fbi.co', ' ', true,  NOW(), 'CGB_S', '$2a$10$MEll2KrFHOSp44JrVOSF5eCiUtRZyi6ZcXMTigbvOV.ayioWbzIie', true, 1);

INSERT INTO comisiones (fecha_inicio, fecha_fin, create_at, fecha_actualizacion, lugar,  justificacion, usuario_id) VALUES (NOW(), NOW(), NOW(), NOW(),'Tunguska','asteroide', 2);
INSERT INTO comisiones (fecha_inicio, fecha_fin, create_at, fecha_actualizacion, lugar, justificacion, idioma, tipo_solicitud_id, usuario_id) VALUES (NOW(), NOW(), NOW(), NOW(),'US', 'asteroide','ingles', 1, 1);
INSERT INTO comisiones (fecha_inicio, fecha_fin, create_at, fecha_actualizacion, lugar, justificacion, idioma, usuario_id) VALUES (NOW(), NOW(), NOW(), NOW(),'Tunguska', 'asteroide','Ruso' , 3);
INSERT INTO comisiones (fecha_inicio, fecha_fin, create_at, fecha_actualizacion, lugar, justificacion, idioma) VALUES (NOW(), NOW(), NOW(), NOW(),'Inglaterra', 'asteroide','ingles');
INSERT INTO comisiones (fecha_inicio, fecha_fin, create_at, fecha_actualizacion, lugar, justificacion, idioma) VALUES (NOW(), NOW(), NOW(), NOW(),'francia', 'asteroide','frances');

INSERT INTO comisiones_estados(create_at, comision_id, estado_id) VALUES (NOW(), 5, 1);
INSERT INTO comisiones_estados(create_at, comision_id, estado_id) VALUES (NOW(), 4, 1);
INSERT INTO comisiones_estados(create_at, comision_id, estado_id) VALUES (NOW(), 4, 2);


INSERT INTO cumplidos (nombre, descripcion, informacion_complementaria, fecha_envio, fecha_confirmacion, correo, comision_id) VALUES ('cumplido_1', 'purity_control', 'black-oil', NOW(), NOW(), '@USSR', 1);

INSERT INTO documentos (nombre, es_anexo, es_cumplido, comision_id) VALUES ('xfile', true, false, 1);
INSERT INTO documentos (nombre, es_anexo, es_cumplido, comision_id) VALUES ('xfile', true, false, 4);








