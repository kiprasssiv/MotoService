CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO motorcycles values (uuid_generate_v4(),'Suzuki','Gs450',1884,true);
INSERT INTO motorcycles values (uuid_generate_v4(),'Suzuki','G50',1984,true);