-- as user postgres
CREATE DATABASE plu_blob
  WITH ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE plu_blob
  IS 'Planned Land Use - blob schema';