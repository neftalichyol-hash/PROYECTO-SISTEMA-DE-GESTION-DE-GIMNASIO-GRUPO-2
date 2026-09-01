/* ============================================================
   PROYECTO:
   SISTEMA DE GESTIÓN DE GIMNASIOS Y RUTINAS PERSONALIZADAS

   BASE DE DATOS:
   GimnasioDB

   MOTOR:
   Microsoft SQL Server
   ============================================================ */

IF DB_ID('GimnasioDB') IS NULL
BEGIN
    CREATE DATABASE GimnasioDB;
    PRINT 'Base de datos GimnasioDB creada correctamente.';
END
ELSE
BEGIN
    PRINT 'La base de datos GimnasioDB ya existe.';
END
GO

USE GimnasioDB;
GO

PRINT 'Base de datos GimnasioDB seleccionada correctamente.';
GO