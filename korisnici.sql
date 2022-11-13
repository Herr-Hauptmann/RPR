BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnik" (
	"id"	INTEGER NOT NULL,
	"ime"	TEXT,
	"prezime"	TEXT,
	"email"	TEXT,
	"username"	TEXT,
	"password"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "korisnik" ("id","ime","prezime","email","username","password") VALUES (1,'Vedran','Ljubović','vljubovic@etf.unsa.ba','vedranlj','test');
INSERT INTO "korisnik" ("id","ime","prezime","email","username","password") VALUES (2,'Amra','Delić','adelic@etf.unsa.ba','amrad','test');
INSERT INTO "korisnik" ("id","ime","prezime","email","username","password") VALUES (3,'Tarik','Sijerčić','tsijercic1@etf.unsa.ba','tariks','test');
INSERT INTO "korisnik" ("id","ime","prezime","email","username","password") VALUES (4,'Rijad','Fejzić','rfejzic1@etf.unsa.ba','rijadf','test');
COMMIT;
