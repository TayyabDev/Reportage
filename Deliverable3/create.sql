#create schema C01Project;
use C01Project;

create table Account(
	accountId int primary key,
    password varchar(20) not null,
    userName varchar(20) not null);

create table User(
	userId Int primary key,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    dateOfBirth date,
    userType enum('T','A'),
    accountId int,
    constraint userfk
		foreign key(accountId)
        references Account(accountId)
        on delete set null
        on update cascade);

create table TEQStaff(
	teqStaffId int primary key,
    constraint teqfk
		foreign key (teqStaffId)
        references User(userId)
        on delete cascade
        on update cascade);

# do we need to include services?
create table Agency(
	agencyId int primary key,
    name varchar(50) not null);

create table Officier(
	officierId int primary key,
    agencyId int,
    constraint officierFk1
		foreign key(OfficierId)
        references User(userId)
        on delete cascade
        on update cascade,
	constraint officierFk2
		foreign key(agencyId)
        references Agency(agencyId)
        on delete set null
        on update cascade);


create table Template(
	templateId int primary key,
    uploadTime timestamp,
    # teq staff id
    reviewerId int,
    agencyId int default 0,
    templateType ENUM('CP', 'NAR', 'CC', 'IO', 'ER', 'LTER', 'LTS', 'LTEX') not null);

create table UniqueIdentifier(
	identifierId int primary key,
    identifierType varchar(255));

insert into UniqueIdentifier values(1, 'FOSS/GCMS Client ID');
insert into UniqueIdentifier values(2, 'Temporary Resident or Ministers Permit Number');
insert into UniqueIdentifier values(3, 'IMM5292, IMM5509, IMM1000 Number');

create table StreetType(
	streetTypeId int not null auto_increment,
    streetType varchar(255),
     primary key (streetTypeId));

insert into StreetType(streetType) values ('Abbey');
insert into StreetType(streetType) values ('Acres');
insert into StreetType(streetType) values ('Allée');
insert into StreetType(streetType) values ('Alley');
insert into StreetType(streetType) values ('Autoroute');
insert into StreetType(streetType) values ('Avenue');
insert into StreetType(streetType) values ('Bay');
insert into StreetType(streetType) values ('Beach');
insert into StreetType(streetType) values ('Bend');
insert into StreetType(streetType) values ('Boulevard');
insert into StreetType(streetType) values ('Bypass');
insert into StreetType(streetType) values ('Byway');
insert into StreetType(streetType) values ('Campus');
insert into StreetType(streetType) values ('Cape');
insert into StreetType(streetType) values ('Carré');
insert into StreetType(streetType) values ('Carrefour');
insert into StreetType(streetType) values ('Centre');
insert into StreetType(streetType) values ('Cercle');
insert into StreetType(streetType) values ('Chase');
insert into StreetType(streetType) values ('Chemin');
insert into StreetType(streetType) values ('Circle');
insert into StreetType(streetType) values ('Circuit');
insert into StreetType(streetType) values ('Close');
insert into StreetType(streetType) values ('Common');
insert into StreetType(streetType) values ('Concession');
insert into StreetType(streetType) values ('Corners');
insert into StreetType(streetType) values ('Côte');
insert into StreetType(streetType) values ('Cour');
insert into StreetType(streetType) values ('Cours');
insert into StreetType(streetType) values ('Court');
insert into StreetType(streetType) values ('Cove');
insert into StreetType(streetType) values ('Crescent ');
insert into StreetType(streetType) values ('Croissant');
insert into StreetType(streetType) values ('Crossing');
insert into StreetType(streetType) values ('CulDeSac');
insert into StreetType(streetType) values ('Dale');
insert into StreetType(streetType) values ('Dell');
insert into StreetType(streetType) values ('Diversion');
insert into StreetType(streetType) values ('Downs');
insert into StreetType(streetType) values ('Drive');
insert into StreetType(streetType) values ('ésplanade');
insert into StreetType(streetType) values ('Estate');
insert into StreetType(streetType) values ('Expressway');
insert into StreetType(streetType) values ('Extension');
insert into StreetType(streetType) values ('Farm');
insert into StreetType(streetType) values ('Field');
insert into StreetType(streetType) values ('Forest');
insert into StreetType(streetType) values ('Freeway');
insert into StreetType(streetType) values ('Front');
insert into StreetType(streetType) values ('Gardens');
insert into StreetType(streetType) values ('Gate');
insert into StreetType(streetType) values ('Glade');
insert into StreetType(streetType) values ('Glen');
insert into StreetType(streetType) values ('Green');
insert into StreetType(streetType) values ('Grounds');
insert into StreetType(streetType) values ('Grove');
insert into StreetType(streetType) values ('Harbour');
insert into StreetType(streetType) values ('Heath');
insert into StreetType(streetType) values ('Heights');
insert into StreetType(streetType) values ('Highlands');
insert into StreetType(streetType) values ('Highway');
insert into StreetType(streetType) values ('Hill');
insert into StreetType(streetType) values ('Holl');
insert into StreetType(streetType) values ('Île');
insert into StreetType(streetType) values ('Impasse');
insert into StreetType(streetType) values ('Inlet');
insert into StreetType(streetType) values ('Iceland');
insert into StreetType(streetType) values ('Key');
insert into StreetType(streetType) values ('Knoll');
insert into StreetType(streetType) values ('Landing');
insert into StreetType(streetType) values ('Lane');
insert into StreetType(streetType) values ('Limits');
insert into StreetType(streetType) values ('Line');
insert into StreetType(streetType) values ('Link');
insert into StreetType(streetType) values ('Lookout');
insert into StreetType(streetType) values ('Loop');
insert into StreetType(streetType) values ('Mall');
insert into StreetType(streetType) values ('Manor');
insert into StreetType(streetType) values ('Maze');
insert into StreetType(streetType) values ('Meadow');
insert into StreetType(streetType) values ('Mews');
insert into StreetType(streetType) values ('Montée');
insert into StreetType(streetType) values ('Moor');
insert into StreetType(streetType) values ('Mount');
insert into StreetType(streetType) values ('Mountain');
insert into StreetType(streetType) values ('Orchard');
insert into StreetType(streetType) values ('Parade');
insert into StreetType(streetType) values ('Parc');
insert into StreetType(streetType) values ('Park');
insert into StreetType(streetType) values ('Parkway');
insert into StreetType(streetType) values ('Passage');
insert into StreetType(streetType) values ('Path');
insert into StreetType(streetType) values ('Pathway');
insert into StreetType(streetType) values ('Pines');
insert into StreetType(streetType) values ('Place');
insert into StreetType(streetType) values ('Platau');
insert into StreetType(streetType) values ('Plaza');
insert into StreetType(streetType) values ('Point');
insert into StreetType(streetType) values ('Pointe');
insert into StreetType(streetType) values ('Port');
insert into StreetType(streetType) values ('Private');
insert into StreetType(streetType) values ('Promenade');
insert into StreetType(streetType) values ('Quai');
insert into StreetType(streetType) values ('Quay');
insert into StreetType(streetType) values ('Ramp');
insert into StreetType(streetType) values ('Rang');
insert into StreetType(streetType) values ('Range');
insert into StreetType(streetType) values ('Ridge');
insert into StreetType(streetType) values ('Rise');
insert into StreetType(streetType) values ('Road');
insert into StreetType(streetType) values ('RondPoint');
insert into StreetType(streetType) values ('Route');
insert into StreetType(streetType) values ('Row');
insert into StreetType(streetType) values ('Rue');
insert into StreetType(streetType) values ('Ruelle');
insert into StreetType(streetType) values ('Run');
insert into StreetType(streetType) values ('Sentier');
insert into StreetType(streetType) values ('Square');
insert into StreetType(streetType) values ('Street');
insert into StreetType(streetType) values ('Subdivision');
insert into StreetType(streetType) values ('Terrace');
insert into StreetType(streetType) values ('Terrasse');
insert into StreetType(streetType) values ('Thicket');
insert into StreetType(streetType) values ('Towers');
insert into StreetType(streetType) values ('Townline');
insert into StreetType(streetType) values ('Trail');
insert into StreetType(streetType) values ('Turnabout');
insert into StreetType(streetType) values ('Vale');
insert into StreetType(streetType) values ('Via');
insert into StreetType(streetType) values ('View');
insert into StreetType(streetType) values ('Village');
insert into StreetType(streetType) values ('Villas');
insert into StreetType(streetType) values ('Vista');
insert into StreetType(streetType) values ('Voie');
insert into StreetType(streetType) values ('Work');
insert into StreetType(streetType) values ('Way');
insert into StreetType(streetType) values ('Wharf');
insert into StreetType(streetType) values ('Wood');
insert into StreetType(streetType) values ('Wynd');
insert into StreetType(streetType) values ('Not Applicable');

create table StreetDirection(
	streetDirectionId int not null auto_increment primary key,
    streetDirection varchar(255));

insert into StreetDirection(streetDirection) values ('E - East');
insert into StreetDirection(streetDirection) values ('N - North');
insert into StreetDirection(streetDirection) values ('NE - Northeast');
insert into StreetDirection(streetDirection) values ('NW - Northwest');
insert into StreetDirection(streetDirection) values ('S - South');
insert into StreetDirection(streetDirection) values ('SE - Southeast');
insert into StreetDirection(streetDirection) values ('SW - Southwest');
insert into StreetDirection(streetDirection) values ('W - West');

create table LanguagePreference(
	preferLanguageId int not null auto_increment primary key,
    languagePreference varchar(255));

insert into LanguagePreference(languagePreference) values ('English');
insert into LanguagePreference(languagePreference) values ('French');
insert into LanguagePreference(languagePreference) values ('Unknown/No preference');

create table ClientProfile(
	`templateId` int primary key,
    constraint CPfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`Unique Identifier` int not null,
    constraint CPfk2 foreign key (`Unique Identifier`) references UniqueIdentifier(identifierId),
	`Unique Identifier Value` char(8) not null,
	`Date of Birth (YYYY-MM-DD)` date not null,
	`Phone Number` varchar(20),
	`Does the Client Have an Email Address` bool,
	`Email Address` varchar(255) default null,
	`Street Number` varchar(5),
	`Street Name` varchar(255),
	`Street Type` int,
    constraint CPfk3 foreign key (`Street Type`) references StreetType(streetTypeId),
	`Street Direction` int,
    constraint CPfk4 foreign key (`Street Direction`) references StreetDirection(streetDirectionId),
	`Unit/Suite/Apt` varchar(10),
	`City` varchar(255),
	`Province` varchar(255),
	`Postal Code` char(6) not null,
	`Official Language of Preference` int not null,
    constraint CPfk5 foreign key (`Official Language of Preference`) references LanguagePreference(preferLanguageId),
	`Consent for Future Research/Consultation` bool not null);

create table ServiceLanguage(
	serviceLanguageId int not null auto_increment primary key,
    serviceLanguage varchar(255));

insert into ServiceLanguage(serviceLanguage) values ('English');
insert into ServiceLanguage(serviceLanguage) values ('French');
insert into ServiceLanguage(serviceLanguage) values ('Ada');
insert into ServiceLanguage(serviceLanguage) values ('Affar');
insert into ServiceLanguage(serviceLanguage) values ('Afrikaans');
insert into ServiceLanguage(serviceLanguage) values ('Aka');
insert into ServiceLanguage(serviceLanguage) values ('Akan');
insert into ServiceLanguage(serviceLanguage) values ('Aklanon');
insert into ServiceLanguage(serviceLanguage) values ('Akra');
insert into ServiceLanguage(serviceLanguage) values ('Albanian');
insert into ServiceLanguage(serviceLanguage) values ('American Sign Language (ASL)');
insert into ServiceLanguage(serviceLanguage) values ('Amharic');
insert into ServiceLanguage(serviceLanguage) values ('Anuak (Anywa)');
insert into ServiceLanguage(serviceLanguage) values ('Arabic');
insert into ServiceLanguage(serviceLanguage) values ('Aran');
insert into ServiceLanguage(serviceLanguage) values ('Armenian');
insert into ServiceLanguage(serviceLanguage) values ('Ashanti');
insert into ServiceLanguage(serviceLanguage) values ('Assyrian');
insert into ServiceLanguage(serviceLanguage) values ('Azeri');
insert into ServiceLanguage(serviceLanguage) values ('Bambara');
insert into ServiceLanguage(serviceLanguage) values ('Bantu');
insert into ServiceLanguage(serviceLanguage) values ('Baule');
insert into ServiceLanguage(serviceLanguage) values ('Belen');
insert into ServiceLanguage(serviceLanguage) values ('Bemba');
insert into ServiceLanguage(serviceLanguage) values ('Bengali');
insert into ServiceLanguage(serviceLanguage) values ('Beni');
insert into ServiceLanguage(serviceLanguage) values ('Benin');
insert into ServiceLanguage(serviceLanguage) values ('Berber');
insert into ServiceLanguage(serviceLanguage) values ('Bicol');
insert into ServiceLanguage(serviceLanguage) values ('Bijaiya');
insert into ServiceLanguage(serviceLanguage) values ('Bini');
insert into ServiceLanguage(serviceLanguage) values ('Bisaya');
insert into ServiceLanguage(serviceLanguage) values ('Bissa');
insert into ServiceLanguage(serviceLanguage) values ('Bontok');
insert into ServiceLanguage(serviceLanguage) values ('Braille (translation only)');
insert into ServiceLanguage(serviceLanguage) values ('Breton');
insert into ServiceLanguage(serviceLanguage) values ('Bulgarian');
insert into ServiceLanguage(serviceLanguage) values ('Burmese');
insert into ServiceLanguage(serviceLanguage) values ('Busan');
insert into ServiceLanguage(serviceLanguage) values ('Busango');
insert into ServiceLanguage(serviceLanguage) values ('Cantonese');
insert into ServiceLanguage(serviceLanguage) values ('Catalan');
insert into ServiceLanguage(serviceLanguage) values ('Cebuano');
insert into ServiceLanguage(serviceLanguage) values ('Chakma');
insert into ServiceLanguage(serviceLanguage) values ('Chaldean');
insert into ServiceLanguage(serviceLanguage) values ('Chaocho');
insert into ServiceLanguage(serviceLanguage) values ('Chavano');
insert into ServiceLanguage(serviceLanguage) values ('Chichewa');
insert into ServiceLanguage(serviceLanguage) values ('Chinese - Simplified (Translation Only)');
insert into ServiceLanguage(serviceLanguage) values ('Chinese - Traditional (TranslationOnly)');
insert into ServiceLanguage(serviceLanguage) values ('Chiuchow');
insert into ServiceLanguage(serviceLanguage) values ('Chiyao');
insert into ServiceLanguage(serviceLanguage) values ('Chowchau');
insert into ServiceLanguage(serviceLanguage) values ('Concani');
insert into ServiceLanguage(serviceLanguage) values ('Creole');
insert into ServiceLanguage(serviceLanguage) values ('Croatian');
insert into ServiceLanguage(serviceLanguage) values ('Czech');
insert into ServiceLanguage(serviceLanguage) values ('Danish');
insert into ServiceLanguage(serviceLanguage) values ('Dari ');
insert into ServiceLanguage(serviceLanguage) values ('Deaf-Mute');
insert into ServiceLanguage(serviceLanguage) values ('Dioula');
insert into ServiceLanguage(serviceLanguage) values ('Dutch');
insert into ServiceLanguage(serviceLanguage) values ('Dzongkha');
insert into ServiceLanguage(serviceLanguage) values ('Edo');
insert into ServiceLanguage(serviceLanguage) values ('Efik');
insert into ServiceLanguage(serviceLanguage) values ('Estonian');
insert into ServiceLanguage(serviceLanguage) values ('Ewe');
insert into ServiceLanguage(serviceLanguage) values ('Facilitator');
insert into ServiceLanguage(serviceLanguage) values ('Fang');
insert into ServiceLanguage(serviceLanguage) values ('Fanti');
insert into ServiceLanguage(serviceLanguage) values ('Farsi (Persian)');
insert into ServiceLanguage(serviceLanguage) values ('Finnish');
insert into ServiceLanguage(serviceLanguage) values ('Flemish');
insert into ServiceLanguage(serviceLanguage) values ('Foochow');
insert into ServiceLanguage(serviceLanguage) values ('Fouki');
insert into ServiceLanguage(serviceLanguage) values ('Foullah');
insert into ServiceLanguage(serviceLanguage) values ('Fukinese');
insert into ServiceLanguage(serviceLanguage) values ('Fulani');
insert into ServiceLanguage(serviceLanguage) values ('Ga');
insert into ServiceLanguage(serviceLanguage) values ('Gaelic');
insert into ServiceLanguage(serviceLanguage) values ('German');
insert into ServiceLanguage(serviceLanguage) values ('Greek');
insert into ServiceLanguage(serviceLanguage) values ('Guerze');
insert into ServiceLanguage(serviceLanguage) values ('Gujarati');
insert into ServiceLanguage(serviceLanguage) values ('Hainam');
insert into ServiceLanguage(serviceLanguage) values ('Hakha Chin');
insert into ServiceLanguage(serviceLanguage) values ('Hakka');
insert into ServiceLanguage(serviceLanguage) values ('Harara');
insert into ServiceLanguage(serviceLanguage) values ('Harary');
insert into ServiceLanguage(serviceLanguage) values ('Hargar');
insert into ServiceLanguage(serviceLanguage) values ('Hassanya');
insert into ServiceLanguage(serviceLanguage) values ('Hausa');
insert into ServiceLanguage(serviceLanguage) values ('Hebrew');
insert into ServiceLanguage(serviceLanguage) values ('Hillgaynon');
insert into ServiceLanguage(serviceLanguage) values ('Hindi');
insert into ServiceLanguage(serviceLanguage) values ('Hindko');
insert into ServiceLanguage(serviceLanguage) values ('Hokkin');
insert into ServiceLanguage(serviceLanguage) values ('Hungarian');
insert into ServiceLanguage(serviceLanguage) values ('Ibibio');
insert into ServiceLanguage(serviceLanguage) values ('Ibo');
insert into ServiceLanguage(serviceLanguage) values ('Icelandic');
insert into ServiceLanguage(serviceLanguage) values ('Igorot');
insert into ServiceLanguage(serviceLanguage) values ('Ilongo');
insert into ServiceLanguage(serviceLanguage) values ('Ilican');
insert into ServiceLanguage(serviceLanguage) values ('Indonesian');
insert into ServiceLanguage(serviceLanguage) values ('Ishan');
insert into ServiceLanguage(serviceLanguage) values ('Italian');
insert into ServiceLanguage(serviceLanguage) values ('Izi');
insert into ServiceLanguage(serviceLanguage) values ('Japanese');
insert into ServiceLanguage(serviceLanguage) values ('Jarai');
insert into ServiceLanguage(serviceLanguage) values ('Javanese');
insert into ServiceLanguage(serviceLanguage) values ('Jolay');
insert into ServiceLanguage(serviceLanguage) values ('Kacchi');
insert into ServiceLanguage(serviceLanguage) values ('Kakwa');
insert into ServiceLanguage(serviceLanguage) values ('Kanarese');
insert into ServiceLanguage(serviceLanguage) values ('Kandahari');
insert into ServiceLanguage(serviceLanguage) values ('Kankani');
insert into ServiceLanguage(serviceLanguage) values ('Karen');
insert into ServiceLanguage(serviceLanguage) values ('Kashmiri');
insert into ServiceLanguage(serviceLanguage) values ('Khmer');
insert into ServiceLanguage(serviceLanguage) values ('Kihavu');
insert into ServiceLanguage(serviceLanguage) values ('Kikongo');
insert into ServiceLanguage(serviceLanguage) values ('Kinyamulenge');
insert into ServiceLanguage(serviceLanguage) values ('Kinyarwanda');
insert into ServiceLanguage(serviceLanguage) values ('Kirundi');
insert into ServiceLanguage(serviceLanguage) values ('Kiswahili');
insert into ServiceLanguage(serviceLanguage) values ('Konkani');
insert into ServiceLanguage(serviceLanguage) values ('Korean');
insert into ServiceLanguage(serviceLanguage) values ('Krio');
insert into ServiceLanguage(serviceLanguage) values ('Kurdish');
insert into ServiceLanguage(serviceLanguage) values ('La langue des signes quÃ©bÃ©coise (LSQ)');
insert into ServiceLanguage(serviceLanguage) values ('Laotian');
insert into ServiceLanguage(serviceLanguage) values ('Latvian');
insert into ServiceLanguage(serviceLanguage) values ('Lebanese');
insert into ServiceLanguage(serviceLanguage) values ('Lengie');
insert into ServiceLanguage(serviceLanguage) values ('Lingala');
insert into ServiceLanguage(serviceLanguage) values ('Lithuanian');
insert into ServiceLanguage(serviceLanguage) values ('Lowna');
insert into ServiceLanguage(serviceLanguage) values ('Luganda');
insert into ServiceLanguage(serviceLanguage) values ('Lugishu');
insert into ServiceLanguage(serviceLanguage) values ('Lutoro');
insert into ServiceLanguage(serviceLanguage) values ('Macedonian ');
insert into ServiceLanguage(serviceLanguage) values ('Macena');
insert into ServiceLanguage(serviceLanguage) values ('Macua');
insert into ServiceLanguage(serviceLanguage) values ('Mahou');
insert into ServiceLanguage(serviceLanguage) values ('Makonde');
insert into ServiceLanguage(serviceLanguage) values ('Malagasy');
insert into ServiceLanguage(serviceLanguage) values ('Malay');
insert into ServiceLanguage(serviceLanguage) values ('Malayalam');
insert into ServiceLanguage(serviceLanguage) values ('Maligo');
insert into ServiceLanguage(serviceLanguage) values ('Malinke');
insert into ServiceLanguage(serviceLanguage) values ('Maltese');
insert into ServiceLanguage(serviceLanguage) values ('Mandarin');
insert into ServiceLanguage(serviceLanguage) values ('Mandingo');
insert into ServiceLanguage(serviceLanguage) values ('Marathi');
insert into ServiceLanguage(serviceLanguage) values ('Mashi');
insert into ServiceLanguage(serviceLanguage) values ('Mende');
insert into ServiceLanguage(serviceLanguage) values ('Mina');
insert into ServiceLanguage(serviceLanguage) values ('Mizo');
insert into ServiceLanguage(serviceLanguage) values ('Mongolian');
insert into ServiceLanguage(serviceLanguage) values ('More');
insert into ServiceLanguage(serviceLanguage) values ('Nepali');
insert into ServiceLanguage(serviceLanguage) values ('Norwegian');
insert into ServiceLanguage(serviceLanguage) values ('Nuer');
insert into ServiceLanguage(serviceLanguage) values ('Nzima');
insert into ServiceLanguage(serviceLanguage) values ('Okpe');
insert into ServiceLanguage(serviceLanguage) values ('Oriya');
insert into ServiceLanguage(serviceLanguage) values ('Oromo');
insert into ServiceLanguage(serviceLanguage) values ('Osal');
insert into ServiceLanguage(serviceLanguage) values ('Other African Languages ');
insert into ServiceLanguage(serviceLanguage) values ('Other Chinese Dialects ');
insert into ServiceLanguage(serviceLanguage) values ('Other European Languages ');
insert into ServiceLanguage(serviceLanguage) values ('Other Languages Nes');
insert into ServiceLanguage(serviceLanguage) values ('Other Middle Eastern Languages');
insert into ServiceLanguage(serviceLanguage) values ('Other Signed Languages ');
insert into ServiceLanguage(serviceLanguage) values ('Other South Asian Languages');
insert into ServiceLanguage(serviceLanguage) values ('Other Western Hemishphere Indian Languages');
insert into ServiceLanguage(serviceLanguage) values ('Otuho');
insert into ServiceLanguage(serviceLanguage) values ('Pahari');
insert into ServiceLanguage(serviceLanguage) values ('Pampango');
insert into ServiceLanguage(serviceLanguage) values ('Pashto');
insert into ServiceLanguage(serviceLanguage) values ('Peul');
insert into ServiceLanguage(serviceLanguage) values ('Phuockien');
insert into ServiceLanguage(serviceLanguage) values ('Pidgin');
insert into ServiceLanguage(serviceLanguage) values ('Plautdietsch (Mennonite Low German)');
insert into ServiceLanguage(serviceLanguage) values ('Polish');
insert into ServiceLanguage(serviceLanguage) values ('Portuguese');
insert into ServiceLanguage(serviceLanguage) values ('Poular');
insert into ServiceLanguage(serviceLanguage) values ('Punjabi');
insert into ServiceLanguage(serviceLanguage) values ('Rohingya');
insert into ServiceLanguage(serviceLanguage) values ('Romani');
insert into ServiceLanguage(serviceLanguage) values ('Romanian');
insert into ServiceLanguage(serviceLanguage) values ('Rukiga');
insert into ServiceLanguage(serviceLanguage) values ('Runyankole');
insert into ServiceLanguage(serviceLanguage) values ('Russian');
insert into ServiceLanguage(serviceLanguage) values ('Rutooro');
insert into ServiceLanguage(serviceLanguage) values ('Samoan');
insert into ServiceLanguage(serviceLanguage) values ('Sango');
insert into ServiceLanguage(serviceLanguage) values ('Scoula');
insert into ServiceLanguage(serviceLanguage) values ('Sechuan');
insert into ServiceLanguage(serviceLanguage) values ('Serbian ');
insert into ServiceLanguage(serviceLanguage) values ('Serbo-Croat-Bosnian');
insert into ServiceLanguage(serviceLanguage) values ('Sesotho');
insert into ServiceLanguage(serviceLanguage) values ('Seswi');
insert into ServiceLanguage(serviceLanguage) values ('Seychelles');
insert into ServiceLanguage(serviceLanguage) values ('Shan');
insert into ServiceLanguage(serviceLanguage) values ('Shanghai');
insert into ServiceLanguage(serviceLanguage) values ('Shansai');
insert into ServiceLanguage(serviceLanguage) values ('Sindhi');
insert into ServiceLanguage(serviceLanguage) values ('Shansai');
insert into ServiceLanguage(serviceLanguage) values ('Sindhi');
insert into ServiceLanguage(serviceLanguage) values ('Sinhalese');
insert into ServiceLanguage(serviceLanguage) values ('Shona ');
insert into ServiceLanguage(serviceLanguage) values ('Slovak');
insert into ServiceLanguage(serviceLanguage) values ('Slovene');
insert into ServiceLanguage(serviceLanguage) values ('Somali');
insert into ServiceLanguage(serviceLanguage) values ('Soninke');
insert into ServiceLanguage(serviceLanguage) values ('Sotho');
insert into ServiceLanguage(serviceLanguage) values ('Soussou');
insert into ServiceLanguage(serviceLanguage) values ('Sothern Burun');
insert into ServiceLanguage(serviceLanguage) values ('Spanish');
insert into ServiceLanguage(serviceLanguage) values ('Suesue');
insert into ServiceLanguage(serviceLanguage) values ('Sukama');
insert into ServiceLanguage(serviceLanguage) values ('Swahili');
insert into ServiceLanguage(serviceLanguage) values ('Swazai');
insert into ServiceLanguage(serviceLanguage) values ('Swedish');
insert into ServiceLanguage(serviceLanguage) values ('Tagalog');
insert into ServiceLanguage(serviceLanguage) values ('Taichew');
insert into ServiceLanguage(serviceLanguage) values ('Tamil');
insert into ServiceLanguage(serviceLanguage) values ('Tari');
insert into ServiceLanguage(serviceLanguage) values ('Tatar');
insert into ServiceLanguage(serviceLanguage) values ('Tetshanese');
insert into ServiceLanguage(serviceLanguage) values ('Telugu');
insert into ServiceLanguage(serviceLanguage) values ('Teochew');
insert into ServiceLanguage(serviceLanguage) values ('Thai');
insert into ServiceLanguage(serviceLanguage) values ('Tibetan');
insert into ServiceLanguage(serviceLanguage) values ('Tichiew');
insert into ServiceLanguage(serviceLanguage) values ('Tigrigna');
insert into ServiceLanguage(serviceLanguage) values ('Timini');
insert into ServiceLanguage(serviceLanguage) values ('Tiv');
insert into ServiceLanguage(serviceLanguage) values ('Toishan');
insert into ServiceLanguage(serviceLanguage) values ('Tshiluba');
insert into ServiceLanguage(serviceLanguage) values ('Twi');
insert into ServiceLanguage(serviceLanguage) values ('Uhrobo');
insert into ServiceLanguage(serviceLanguage) values ('Ulgrigma');
insert into ServiceLanguage(serviceLanguage) values ('Ukranoan');
insert into ServiceLanguage(serviceLanguage) values ('Umbundu');
insert into ServiceLanguage(serviceLanguage) values ('Unama');
insert into ServiceLanguage(serviceLanguage) values ('Urdu');
insert into ServiceLanguage(serviceLanguage) values ('Uzbek');
insert into ServiceLanguage(serviceLanguage) values ('Vietnamese');
insert into ServiceLanguage(serviceLanguage) values ('Visayan');
insert into ServiceLanguage(serviceLanguage) values ('Waray');
insert into ServiceLanguage(serviceLanguage) values ('Welsh');
insert into ServiceLanguage(serviceLanguage) values ('Wolof');
insert into ServiceLanguage(serviceLanguage) values ('Xhosa');
insert into ServiceLanguage(serviceLanguage) values ('Yiboe');
insert into ServiceLanguage(serviceLanguage) values ('Yiddish');
insert into ServiceLanguage(serviceLanguage) values ('Yoruba');
insert into ServiceLanguage(serviceLanguage) values ('Zshiluba');
insert into ServiceLanguage(serviceLanguage) values ('Zuganda');
insert into ServiceLanguage(serviceLanguage) values ('Zulu');

create table InstitutionType(
	institutionTypeId int not null auto_increment primary key,
    institutionType varchar(255));

insert into InstitutionType(institutionType) values ('Settlement service provider');
insert into InstitutionType(institutionType) values ('Public library');
insert into InstitutionType(institutionType) values ('Elementary school');
insert into InstitutionType(institutionType) values ('Secondary school');
insert into InstitutionType(institutionType) values ('Post-secondary institution');
insert into InstitutionType(institutionType) values ('Healthcare institution');
insert into InstitutionType(institutionType) values ('Community centre/neighbourhood house');
insert into InstitutionType(institutionType) values ('Employment agency');
insert into InstitutionType(institutionType) values ('The clientâ€™s home');
insert into InstitutionType(institutionType) values ('The clientâ€™s place of employment');
insert into InstitutionType(institutionType) values ('A public space (shopping centre, etc.)');
insert into InstitutionType(institutionType) values ('Port of entry');
insert into InstitutionType(institutionType) values ('Online');
insert into InstitutionType(institutionType) values ('Not for profit organization');

create table Referrer(
	referrerId int not null auto_increment primary key,
    refferer varchar(255));

insert into Referrer(refferer) values ('Community centre / library');
insert into Referrer(refferer) values ('Employer / co-worker');
insert into Referrer(refferer) values ('Ethnic or religious organization');
insert into Referrer(refferer) values ('Family / friends');
insert into Referrer(refferer) values ('Canadian government agency');
insert into Referrer(refferer) values ('Government publication / brochure / website');
insert into Referrer(refferer) values ('Immigration consultant / lawyer');
insert into Referrer(refferer) values ('Internal to my organization');
insert into Referrer(refferer) values ('Non-governmental newspaper / media / publication / brochure / Web site');
insert into Referrer(refferer) values ('In-Canada orientation session');
insert into Referrer(refferer) values ('Overseas orientation session (e.g. CIIP)');
insert into Referrer(refferer) values ('Other settlement service provider');
insert into Referrer(refferer) values ('School');
insert into Referrer(refferer) values ('Not referred');

create table Reason(
	ReasonId int not null auto_increment primary key,
    reason varchar(255));
    
insert into Reason(reason) values ('Find employment');
insert into Reason(reason) values ('Get an education');
insert into Reason(reason) values ('Participate in Canadian society');
insert into Reason(reason) values ('Acquire citizenship');

create table TimeFrame(
	timeFrameId int not null auto_increment primary key,
    timeFrame varchar(255));

insert into TimeFrame(timeFrame) values ('Within one year');
insert into TimeFrame(timeFrame) values ('After one year');

create table WorkExperience(
	workExperienceId int not null auto_increment primary key,
    workExperience varchar(255));
    
insert into WorkExperience(workExperience) values ('No');
insert into WorkExperience(workExperience) values ('Yes, within Canada');
insert into WorkExperience(workExperience) values ('Yes, outside Canada');
insert into WorkExperience(workExperience) values ('Yes, both within and outside Canada');

create table Occupation(
	OccupationId int not null auto_increment primary key,
    Occupation varchar(255));
    
insert into Occupation(occupation) values ('O - Management occupations');
insert into Occupation(occupation) values ('A - Professional');
insert into Occupation(occupation) values ('B - Skilled and technical');
insert into Occupation(occupation) values ('C - Intermediate and clerical');
insert into Occupation(occupation) values ('D - Elemental and labourers');
insert into Occupation(occupation) values ('Unknown/not sure');

create table Answer(
	answerId int not null auto_increment primary key,
    answer varchar(255));

insert into Answer(answer) values ('Yes');
insert into Answer(answer) values ('No');
insert into Answer(answer) values ('Unknown/Not sure');

create table Age(
	ageId int not null auto_increment primary key,
    age varchar(255));

insert into Age(age) values ('Infant (6-18 months)');
insert into Age(age) values ('Toddler (19-35 months)');
insert into Age(age) values ('Pre-school (36 months - 6 years)');
insert into Age(age) values ('School age (more than 6 years)');
insert into Age(age) values ('Children (0-14 yrs)');
insert into Age(age) values ('Youth (15-24 yrs)');
insert into Age(age) values ('Adult');
insert into Age(age) values ('Senior');

create table CareType(
	careTypeId int not null auto_increment primary key,
    careType varchar(255));
insert into CareType(careType) values ('Short term');
insert into CareType(careType) values ('Long term');

create table ReasonUpdate(
	reasonUpdateId int not null auto_increment primary key,
    reasonUpdate varchar(255));
insert into ReasonUpdate(reasonUpdate) values ('Amend record');
insert into ReasonUpdate(reasonUpdate) values ('Correct error');

create table NeedsAssessmentAndReferrals(
	`templateId` int primary key,
    constraint NAARfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`Update Record ID` char(8), # does it need to be 8 digits
	`Unique Identifier` int not null,
    constraint NAARfk2 foreign key (`Unique Identifier`) references UniqueIdentifier(identifierId),
	`Unique Identifier Value` char(8) not null,
	`Date of Birth (YYYY-MM-DD)` date not null,
	`Postal Code where the service was received` char(6) not null,
	`Start Date of Assessment (YYYY-MM-DD)` date not null,
	`Language of Service` int not null,
    constraint NAARfk3 foreign key (`Language of Service`) references ServiceLanguage(serviceLanguageId),
	`Official Language of Preference` int not null,
    constraint NAARfk4 foreign key (`Official Language of Preference`) references LanguagePreference(preferLanguageId),
	`Type of Institution/Organization Where Client Received Services` int not null,
	constraint NAARfk5 foreign key (`Type of Institution/Organization Where Client Received Services`) references InstitutionType(institutionTypeId),
	`Referred By`int not null,
    constraint NAARfk6 foreign key (`Referred By`) references Referrer(reffererId),
	`Increase knowledge of: Life in Canada` bool,
	`Increase knowledge of: Life in Canada Referrals` bool,
	`Increase knowledge of: Community and Government Services` bool,
	`Increase knowledge of: Community and Government Services Referrals` bool,
	`Increase knowledge of: Working in Canada` bool,
	`Increase knowledge of: Working in Canada Referrals` bool,
	`Increase knowledge of: Education in Canada` bool,
	`Increase knowledge of: Education in Canada Referrals` bool,
	`Increase the following: Social networks` bool,
	`Increase the following: Social networks Referrals` bool,
	`Increase the following: Professional networks` bool,
	`Increase the following: Professional networks Referrals` bool,
	`Increase the following: Access to local community services` bool,
	`Increase the following: Access to local community services Referrals` bool,
	`Increase the following: Level of community involvement` bool,
	`Increase the following: Level of community involvement Referrals` bool,
	`Improve Language Skills` bool,
	`Improve Language Skills Referrals` bool,
	`Improve Language Skills to` int,
    constraint NAARfk7 foreign key (`Improve Language Skills to`) references Reason(reasonId),
	`Improve Other Skills` bool,
	`Improve Other Skills Referrals` bool,
	`Improve Other Skills to` int,
    constraint NAARfk8 foreign key (`Improve Other Skills to`) references Reason(reasonId),
	`Find employment` bool,
	`Find employment Referrals` bool,
	`Find employment: TimeFrame` int,
    constraint NAARfk9 foreign key (`Find employment: TimeFrame`) references TimeFrame(timeFrameId),
	`Find employment: Minimum one year's work experience?` int,
    constraint NAARfk10 foreign key (`Find employment: Minimum one year's work experience?`) references WorkExperience(workExperienceId),
	`Find employment: Intends to work in an occupation corresponding to which National Occupation Classification skill level?` int,
    constraint NAARfk11 
		foreign key (`Find employment: Intends to work in an occupation corresponding to which National Occupation Classification skill level?`) 
		references Occupation(occupationId),
	`Find employment: Intends to obtain credential recognition or obtain license to work in Canada?` int,
    constraint NAARfk12
		foreign key (`Find employment: Intends to obtain credential recognition or obtain license to work in Canada?`) 
		references Answer(answerId),
	`Client intends to become a Canadian citizen?` int not null,
    constraint NAARfk13
		foreign key (`FClient intends to become a Canadian citizen?`) 
		references Answer(answerId),
	`Support services may be required` bool not null,
	`Care for Newcomer Children` bool,
	`Transportation` bool,
	`Provisions for Disabilities` bool,
	`Translation` bool,
	`Interpretation` bool,
	`Crisis Counselling` bool,
	`Non-IRCC program services needed` bool not null,
	`Food/Clothing/Other Material Needs` bool,
	`Food/Clothing/Other Material Needs Referrals` bool,
	`Housing/Accommodation` bool,
	`Housing/Accommodation Referrals` bool,
	`Health/Mental Health/Well Being` bool,
	`Health/Mental Health/Well Being Referrals` bool,
	`Financial` bool,
	`Financial Referrals` bool,
	`Family Support` bool,
	`Family Support Referrals` bool,
	`Language (Non-IRCC)` bool,
	`Language (Non-IRCC) Referrals` bool,
	`Education/Skills Development` bool,
	`Education/Skills Development Referrals` bool,
	`Employment-related` bool,
	`Employment-related Referrals` bool,
	`Legal Information and Services` bool,
	`Legal Information and Services Referrals` bool,
	`Community Services` bool,
	`Community Services Referrals` bool,
	`Support Services Received` bool not null,
	`Care for Newcomer Children` bool,
	`Child 1: Age` int,
    constraint NAARfk14
		foreign key (`Child 1: Age`) 
		references Age(ageId),
	`Child 1: Type of Care` int,
    constraint NAARfk15
		foreign key (`Child 1: Type of Care`) 
		references CareType(careTypeId),
	`Child 2: Age` int,
    constraint NAARfk16
		foreign key (`Child 2: Age`) 
		references Age(ageId),
	`Child 2: Type of Care` int,
    constraint NAARfk17
		foreign key (`Child 2: Type of Care`) 
		references CareType(careTypeId),
	`Child 3: Age` int,
    constraint NAARfk18
		foreign key (`Child 3: Age`) 
		references Age(ageId),
	`Child 4: Type of Care` int,
    constraint NAARfk19
		foreign key (`Child 4: Type of Care`) 
		references CareType(careTypeId),
	`Child 5: Age` int,
    constraint NAARfk20
		foreign key (`Child 5: Age`) 
		references Age(ageId),
	`Child 5: Type of Care` int,
    constraint NAARfk15
		foreign key (`Child 5: Type of Care`) 
		references CareType(careTypeId),
	`Transportation` bool,
	`Provisions for Disabilities` bool,
	`Translation` bool,
    `Translation: Between` int not null,
    constraint NAARfk16 foreign key (`Translation: Between`) references ServiceLanguage(serviceLanguageId),
	`Translation: And`int not null,
    constraint NAARfk17 foreign key (`Translation: And`) references ServiceLanguage(serviceLanguageId),
	`Interpretation` bool,
    `Interpretation: Between` int not null,
    constraint NAARfk18 foreign key (`Interpretation: Between`) references ServiceLanguage(serviceLanguageId),
	`Interpretation: And`int not null,
    constraint NAARfk19 foreign key (`Interpretation: And`) references ServiceLanguage(serviceLanguageId),
	`Crisis Counselling` bool,
	`Settlement Plan completed and shared with client` bool not null,
	`End Date of Assessment (YYYY-MM-DD)` date not null,
	`Reason for update` int,
    constraint NAARfk20 foreign key (`Reason for update`) references ReasonUpdate(reasonUpdateId));
#create table Employment();
#create table InfoAndOrientation();
#create table CommunityConnections();
#create table LTEnrolment();
create table LTCourseSetup(
	`templateId` int primary key,
    constraint LTCS1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`processingDetails` varchar(255),
	`updateRecordID` int(10),
	`courseCode` varchar(25) not null,
	`notes` varchar(255),
	`courseHeldOnAnOngoingBasis` bool not null,
	`officialLanguageOfCourse` int not null, 
    constraint LTCS2 foreign key (`officialLanguageOfCourse`) references ServiceLanguage(serviceLanguageId),
	`formatOfTrainingProvided` int not null,
    constraint LTCS3 foreign key (`formatOfTrainingProvided`) references FormatOfTrainingProvided(trainingProvidedId),
	`classesHeldAt` varchar(255),
	`inPersonInstruction(%)` decimal(5, 2) check (`inPersonInstruction(%)` > 0 and `inPersonInstruction(%)` <= 100),
	`onlineDistanceInstruction(%)` decimal(5, 2) check (`onlineDistanceInstruction(%)` > 0 and `onlineDistanceInstruction(%)` <= 100),
	`totalNumberofSpotsinCourse` int not null,
	`numberOfIRCCFundedSpotsinCourse` int not null,
	`newStudentsCanEnrolInTheCourse` int not null,
    constraint LTCS4 foreign key (`newStudentsCanEnrolInTheCourse`) references NewEnrollmentInCourseFrequency(frequencyId),
	`supportServicesAvailableForClientInThisCourse` bool not null,
	`careForNewcomerChildren` bool,
	`transportation` bool,
	`provisionsForDisabilities` bool,
	`courseStartDate` date not null,
	`courseEndDate` date not null,
	`scheduleMorning` bool,
	`scheduleAfternoon` bool,
	`scheduleEvening` bool,
	`scheduleWeekend` bool,
	`scheduleAnytime` bool,
	`scheduleOnline` bool,
	`instructionalHoursPerClass` decimal(5, 2) not null,
	`classesPerWeek` int not null,
	`weeksOfInstruction` int,
	`weeksOfInstructionPerYear` int,
	`dominantFocusOfTheCourse` varchar(255) not null,
	`courseDirectedAtASpecificTargetGroup` bool not null,
	`children (0-14 yrs)` bool,
	`youth (15-24 yrs)` bool,
	`senior` bool,
	`genderSpecific` bool,
	`refugees` bool,
	`officialLanguageMinorities` bool,
	`ethnicCulturalLinguisticGroup` bool,
	`deafOrHardOfHearing` bool,
	`blindOrPartiallySighted` bool,
	`clientsWithOtherImpairments (physical, mental)` bool,
	`lesbianGayBisexualTransgenderQueer (LGBTQ)` bool,
	`familiesParents` bool,
	`clientsWithInternationalTrainingInARegulatedProfession` bool,
	`clientsWithInternationalTrainingInARegulatedTrade` bool,
	`materialsUsedInCourse` bool not null,
	`citizenshipPreparation` bool,
	`PBLALanguageCompanion` bool,
	`contactName` varchar(255),
	`streetNumber` int not null,
	`streetName` varchar(255) not null,
	`streetType` int not null,
    constraint LTCS5 foreign key (`streetType`) references StreetType(streetTypeId),
	`streetDirection` int,
    constraint LTCS6 foreign key (`streetDirection`) references StreetDirection(streetDirectionId),
	`unitSuite` int,
	`province` varchar(255) not null,
	`city` varchar(255) not null,
	`postalCode` char(6) not null,
	`telephoneNumber` int(20) not null,
	`telephoneExtension` int(3),
	`emailAddress` varchar(255) not null,
	`listeningSkillLevel1` int,
	`listeningSkillLevel2` int,
	`listeningSkillLevel3` int,
	`listeningSkillLevel4` int,
	`listeningSkillLevel5` int,
	`listeningSkillLevel6` int,
	`listeningSkillLevel7` int,
	`listeningSkillLevel8` int,
	`listeningSkillLevel9` int,
	`listeningSkillLevel10` int,
	`listeningSkillLevel11` int,
	`listeningSkillLevel12` int,
	`speakingSkillLevel1` int,
	`speakingSkillLevel2` int,
	`speakingSkillLevel3` int,
	`speakingSkillLevel4` int,
	`speakingSkillLevel5` int,
	`speakingSkillLevel6` int,
	`speakingSkillLevel7` int,
	`speakingSkillLevel8` int,
	`speakingSkillLevel9` int,
	`speakingSkillLevel10` int,
	`speakingSkillLevel11` int,
	`speakingSkillLevel12` int,
	`readingSkillLevel1` int,
	`readingSkillLevel2` int,
	`readingSkillLevel3` int,
	`readingSkillLevel4` int,
	`readingSkillLevel5` int,
	`readingSkillLevel6` int,
	`readingSkillLevel7` int,
	`readingSkillLevel8` int,
	`readingSkillLevel9` int,
	`readingSkillLevel10` int,
	`readingSkillLevel11` int,
	`readingSkillLevel12` int,
	`readingSkillLevel13` int,
	`readingSkillLevel14` int,
	`readingSkillLevel15` int,
	`readingSkillLevel16` int,
	`readingSkillLevel17` int,
	`writingSkillLevel1` int,
	`writingSkillLevel2` int,
	`writingSkillLevel3` int,
	`writingSkillLevel4` int,
	`writingSkillLevel5` int,
	`writingSkillLevel6` int,
	`writingSkillLevel7` int,
	`writingSkillLevel8` int,
	`writingSkillLevel9` int,
	`writingSkillLevel10` int,
	`writingSkillLevel11` int,
	`writingSkillLevel12` int,
	`writingSkillLevel13` int,
	`writingSkillLevel14` int,
	`writingSkillLevel15` int,
	`writingSkillLevel16` int,
	`writingSkillLevel17` int
);

create table FormatOfTrainingProvided(
	trainingProvidedId int not null auto_increment primary key,
    trainingProvided varchar(255));
    
create table NewEnrollmentInCourseFrequency(
	frequencyId int not null auto_increment primary key,
    frequency varchar(255));

insert into NewEnrollmentInCourseFrequency(frequency) values ('Daily');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Weekly');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Biweekly');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Monthly');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Bimonthly');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Quarterly');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Semianually');
insert into NewEnrollmentInCourseFrequency(frequency) values ('Anually');

create table LTClientExit(
	`templateId` int primary key,
    constraint LTCE1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`processingDetails` varchar(255),
	`updateRecordID` int(10),
	`uniqueIdentifierType` varchar(255) not null,
	`uniqueIdentifierValue` int(10) not null,
	`clientDateOfBirth` date not null,
	`courseCode` varchar(255) not null,
	`clientTrainingStatus` varchar(255) not null,
	`dateClientExitedCourse` date,
	`reasonForExitingCourse` varchar(255),
	`listeningCLBLevel` int,
	`speakingCLBLevel` int,
	`readingCLBLevel` int,
	`writingCLBLevel` int,
	`wasACertificateIssuedToTheClient` bool not null,
	`listeningLevelIndicatedOnCertificate` int,
	`speakingLevelIndicatedOnCertificate` int,
	`supportServicesReceived` bool not null,
	`careForNewcomerChildren` bool,
	`child1Age` int,
    constraint LTCE2 foreign key (`child1Age`) references Age(ageId),
	`child1TypeOfCare` int,
    constraint LTCE3 foreign key (`child1TypeOfCare`) references CareType(careTypeId),
	`child2Age` int,
	constraint LTCE4 foreign key (`child2Age`) references Age(ageId),
	`child2TypeOfCare` int,
    constraint LTCE5 foreign key (`child2TypeOfCare`) references CareType(careTypeId),
	`child3Age` int,
	constraint LTCE6 foreign key (`child3Age`) references Age(ageId),
	`child3TypeOfCare` int,
    constraint LTCE7 foreign key (`child3TypeOfCare`) references CareType(careTypeId),
	`child4Age` int,
	constraint LTCE8 foreign key (`child4Age`) references Age(ageId),
	`child4TypeOfCare` int,
    constraint LTCE9 foreign key (`child4TypeOfCare`) references CareType(careTypeId),
	`child5Age` int,
	constraint LTCE10 foreign key (`child5Age`) references Age(ageId),
	`child5TypeOfCare` int,
    constraint LTCE11 foreign key (`child5TypeOfCare`) references CareType(careTypeId),
	`transportation` bool,
	`provisionsForDisabilities` bool,
	`translation` bool,
	`translationLanguageBetween` int,
    constraint LTCE12 foreign key (`translationLanguageBetween`) references ServiceLanguage(serviceLanguageId),
	`translationLanguageAnd` int,
    constraint LTCE13 foreign key (`translationLanguageAnd`) references ServiceLanguage(serviceLanguageId),
	`interpretation` bool,
	`interpretationBetween`int,
    constraint LTCE14 foreign key (`interpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`interpretationAnd` int,
    constraint LTCE15 foreign key (`interpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`crisisCounselling` bool,
	`reasonForUpdat` varchar(255)
);