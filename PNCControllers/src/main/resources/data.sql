INSERT INTO "sec01_categories" VALUES ('CT_AVR', 'Aventura') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_TRR', 'Terror') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_ACC', 'Acción') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_CYF', 'Ciencia Ficcón') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_FNT', 'Fantasía') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_MST', 'Misterio') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_SUS', 'Suspenso') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_DRM', 'Drama') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_DOC', 'Documentación') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_SCS', 'Ciencia') ON CONFLICT (code) DO UPDATE set "name" = excluded."name";