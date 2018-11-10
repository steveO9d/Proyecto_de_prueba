-- Generado por Oracle SQL Developer Data Modeler 18.2.0.179.0806
--   en:        2018-11-03 17:21:33 CST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g

CREATE TABLE producto (
    codigo            VARCHAR2(45) NOT NULL,
    nombre            VARCHAR2(45),
    importado         INTEGER,
    precio            NUMBER,
    porcentaje        NUMBER,
    impuesto          NUMBER,
    precioFinal       NUMBER,
    tipo_nombretipo   VARCHAR2(45) NOT NULL
)
LOGGING;

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( codigo );

CREATE TABLE tipo (
    nombretipo   VARCHAR2(45) NOT NULL
)
LOGGING;

ALTER TABLE tipo ADD CONSTRAINT tipo_pk PRIMARY KEY ( nombretipo );

ALTER TABLE producto
    ADD CONSTRAINT producto_tipo_fk FOREIGN KEY ( tipo_nombretipo )
        REFERENCES tipo ( nombretipo )
    NOT DEFERRABLE;

INSERT INTO tipo VALUES('Can. Básica');
INSERT INTO tipo VALUES('Popular');
INSERT INTO tipo VALUES('Suntuario');
SELECT * FROM tipo;


CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION listarProductos
return Types.ref_cursor 
as 
        producto_cursor types.ref_cursor; 
begin 
  open producto_cursor for 
       SELECT * FROM tipo t INNER JOIN producto p ON p.tipo_nombretipo = t.nombretipo;
return producto_cursor;
end; 
/


CREATE OR REPLACE PROCEDURE insertarProductos(codigo IN producto.codigo%TYPE,  nombre IN producto.nombre%TYPE, importado IN producto.importado%TYPE, precio IN producto.precio%TYPE, porcentaje IN producto.porcentaje%TYPE,  impuesto IN producto.impuesto%TYPE,precioFinal IN producto.precioFinal%TYPE, tipo_nombretipo IN producto.tipo_nombretipo%TYPE)
AS
BEGIN
	INSERT INTO producto VALUES(codigo, nombre,importado, precio, porcentaje, impuesto, precioFinal, tipo_nombretipo);
END;
/
call insertarProductos('codigo2', 'prueba2', 0, 0, 0.5,0.2,100, 'Suntuario');
call insertarProductos('3y3y3y3y', 'Frijoles', 1, 1, 0.5,0.2,100, 'Popular');
SELECT * FROM producto;
------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION listarTipos
return Types.ref_cursor 
as 
        tipo_cursor types.ref_cursor; 
begin 
  open tipo_cursor for 
       SELECT * FROM tipo;
return tipo_cursor;
end; 
/

delete from producto where codigo='3';






