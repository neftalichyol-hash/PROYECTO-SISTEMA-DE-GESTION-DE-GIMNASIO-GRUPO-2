ALTER TABLE dbo.Equipamiento DROP CONSTRAINT DF__Equipamie__Estad__44FF419A;

ALTER TABLE dbo.Equipamiento ALTER COLUMN Estado varchar(30) NULL;