#create schema cscc43s18_linjun9;
use cscc43s18_linjun9;

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
	templateId int auto_increment primary key,
    updateTime timestamp default now(),
#    teqStaffId int,
#    constraint Templatefk1
#		foreign key (teqStaffId)
#        references TEQStaff(teqStaffId),
    templateName varchar(255),
    tableName varchar(255));

create table ClientDataForm(
	clientDataFormId int auto_increment primary key,
    templateId int,
    constraint CDFFk1
		foreign key(templateId)
        references Template(templateId),
    uploadTime timestamp default now(),
    reviewerId int,
    constraint CDFFk2
		foreign key(reviewerId)
        references TEQStaff(teqStaffId),
    agencyId int,
    constraint CDFFk3
		foreign key(agencyId)
        references Agency(agencyId),
    year year(4),
    month int(2) check (month > 0 and month < 13));


    
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
	`templateId` int,
    constraint CPfk1
		foreign key(`templateId`)
        references Template(templateId)
        on delete no action
        on update cascade,
	`CP1UniqueID` int not null,
    constraint CPfk2 foreign key (`CP1UniqueID`) references UniqueIdentifier(identifierId),
	`CP1UniqueIDValue` char(8) not null,
    primary key (`templateId`, `CP1UniqueID`, `CP1UniqueIDValue`),
	`CP1DOB` date not null,
	`CP1PhoneNum` varchar(20),
	`CP1QuesEmailAddress` bool,
	`CP1EmailAddress` varchar(255) default null,
	`CP1StreetNum` varchar(5),
	`CP1StreetName` varchar(255),
	`CP1StreetType` int,
    constraint CPfk3 foreign key (`CP1StreetType`) references StreetType(streetTypeId),
	`CP1StreetDirection` int,
    constraint CPfk4 foreign key (`CP1StreetDirection`) references StreetDirection(streetDirectionId),
	`CP1UnitNum` varchar(10),
	`CP1City` varchar(255),
	`CP1Province` varchar(255),
	`CP1PostalCode` char(6) not null,
	`CP1OfficialLanguagePrefered` int not null,
    constraint CPfk5 foreign key (`CP1OfficialLanguagePrefered`) references LanguagePreference(preferLanguageId),
	`CP1ConsetForResearch` bool not null);

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
	`templateId` int,
    constraint NAARfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`NAR1UpdateRecordID` char(8),
	`NAR1UniqueID` int not null,
    constraint NAARfk2 foreign key (`NAR1UniqueID`) references UniqueIdentifier(identifierId),
	`NAR1UniqueIDValue` char(8) not null,
    primary key (`templateId`, `NAR1UniqueID`, `NAR1UniqueIDValue`),
	`NAR1DOB` date not null,
	`NAR1PostalCode` char(6) not null,
	`NAR1StartDate` date not null,
	`NAR1ServiceLanguage` int not null,
    constraint NAARfk3 foreign key (`NAR1ServiceLanguage`) references ServiceLanguage(serviceLanguageId),
	`NAR1OfficialLanguagePrefered` int not null,
    constraint NAARfk4 foreign key (`NAR1OfficialLanguagePrefered`) references LanguagePreference(preferLanguageId),
	`NAR1InstitutionType` int not null,
	constraint NAARfk5 foreign key (`NAR1InstitutionType`) references InstitutionType(institutionTypeId),
	`NAR1ReferredBy`int not null,
    constraint NAARfk6 foreign key (`NAR1ReferredBy`) references Referrer(referrerId),
	`NAR1IncKnowledgeLifeInCanada` bool,
	`NAR1IncKnowledgeLifeInCanadaRef` bool,
	`NAR1IncKnowledgeCommunityService` bool,
	`NAR1IncKnowledgeCommunityServiceRef` bool,
	`NAR1IncKnowledgeWorkInCanada` bool,
	`NAR1IncKnowledgeWorkInCanadaRef` bool,
	`NAR1IncKnowledgeEduInCanada` bool,
	`NAR1IncKnowledgeEduInCanadaRef` bool,
	`NAR1IncSocialNetworks` bool,
	`NAR1IncSocialNetworksRef` bool,
	`NAR1IncProfessionalNetworks` bool,
	`NAR1IncProfessionalNetworksRef` bool,
	`NAR1IncLocalCommunityService` bool,
	`NAR1IncLocalCommunityServiceRef` bool,
	`NAR1IncCommunityInvolvement` bool,
	`NAR1IncCommunityInvolvementRef` bool,
	`NAR1ImpLanguageSkill` bool,
	`NAR1ImpLanguageSkillRef` bool,
	`NAR1ReasonLanguageSkill` int,
    constraint NAARfk7 foreign key (`NAR1ReasonLanguageSkill`) references Reason(reasonId),
	`NAR1ImpOtherSkill` bool,
	`NAR1ImpOtherSkillRef` bool,
	`NAR1ReasonOtherSkill` int,
    constraint NAARfk8 foreign key (`NAR1ReasonOtherSkill`) references Reason(reasonId),
	`NAR1FindEmployment` bool,
	`NAR1FindEmploymentRef` bool,
	`NAR1TimeFrame` int,
    constraint NAARfk9 foreign key (`NAR1TimeFrame`) references TimeFrame(timeFrameId),
	`NAR1WorkExp` int,
    constraint NAARfk10 foreign key (`NAR1WorkExp`) references WorkExperience(workExperienceId),
	`NAR1IntendWorkOccupation` int,
    constraint NAARfk11 
		foreign key (`NAR1IntendWorkOccupation`)
		references Occupation(occupationId),
	`NAR1IntendObtainCredential` int,
    constraint NAARfk12
		foreign key (`NAR1IntendObtainCredential`) 
		references Answer(answerId),
	`NAR1IntendCanadianCitizen` int not null,
    constraint NAARfk13
		foreign key (`NAR1IntendCanadianCitizen`) 
		references Answer(answerId),
	`NAR1SupportServiceRequired` bool not null,
	`NAR1CareChildren` bool,
	`NAR1Transportation` bool,
	`NAR1Disability` bool,
	`NAR1Translation` bool,
	`NAR1Interpretation` bool,
	`NAR1CrisisCounselling` bool,
	`NAR1NonIRCCServicesNeeded` bool not null,
	`NAR1MaterialNeeds` bool,
	`NAR1MaterialNeedsRef` bool,
	`NAR1Accommodation` bool,
	`NAR1AccommodationRef` bool,
	`NAR1Health` bool,
	`NAR1HealthRef` bool,
	`NAR1Financial` bool,
	`NAR1FinancialRef` bool,
	`NAR1FamilySupport` bool,
	`NAR1FamilySupportRef` bool,
	`NAR1LanguageNonIRCC` bool,
	`NAR1LanguageNonIRCCRef` bool,
	`NAR1Education` bool,
	`NAR1EducationRef` bool,
	`NAR1EmploymentRelated` bool,
	`NAR1EmploymentRelatedRef` bool,
	`NAR1LegalInfoAndService` bool,
	`NAR1LegalInfoAndServiceRef` bool,
	`NAR1CommunityService` bool,
	`NAR1CommunityServiceRef` bool,
	`NAR1SupportServiceReceived` bool not null,
	`NAR2CareChildren` bool,
	`NAR1Child1Age` int,
    constraint NAARfk14
		foreign key (`NAR1Child1Age`) 
		references Age(ageId),
	`NAR1Child1Type` int,
    constraint NAARfk15
		foreign key (`NAR1Child1Type`) 
		references CareType(careTypeId),
	`NAR1Child2Age` int,
    constraint NAARfk16
		foreign key (`NAR1Child2Age`) 
		references Age(ageId),
	`NAR1Child2Type` int,
    constraint NAARfk17
		foreign key (`NAR1Child2Type`) 
		references CareType(careTypeId),
	`NAR1Child3Age` int,
    constraint NAARfk18
		foreign key (`NAR1Child3Age`) 
		references Age(ageId),
	`NAR1Child3Type` int,
    constraint NAARfk19
		foreign key (`NAR1Child3Type`) 
		references CareType(careTypeId),
	`NAR1Child4Age` int,
    constraint NAARfk20
		foreign key (`NAR1Child4Age`) 
		references Age(ageId),
	`NAR1Child4Type` int,
    constraint NAARfk21
		foreign key (`NAR1Child4Type`) 
		references CareType(careTypeId),
	`NAR1Child5Age` int,
    constraint NAARfk22
		foreign key (`NAR1Child5Age`) 
		references Age(ageId),
	`NAR1Child5Type` int,
    constraint NAARfk23
		foreign key (`NAR1Child5Type`) 
		references CareType(careTypeId),
	`NAR2Transportation` bool,
	`NAR2Disability` bool,
	`NAR2Translation` bool,
    `NAR1TranslationBetween` int not null,
    constraint NAARfk24 foreign key (`NAR1TranslationBetween`) references ServiceLanguage(serviceLanguageId),
	`NAR1TranslationAnd`int not null,
    constraint NAARfk25 foreign key (`NAR1TranslationAnd`) references ServiceLanguage(serviceLanguageId),
	`NAR2Interpretation` bool,
    `NAR1InterpretationBetween` int not null,
    constraint NAARfk26 foreign key (`NAR1InterpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`NAR1InterpretationAnd`int not null,
    constraint NAARfk27 foreign key (`NAR1InterpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`NAR2CrisisCounselling` bool,
	`NAR1SettlementPlanCompleted` bool not null,
	`NAR1EndDate` date not null,
	`NAR1ReasonUpdate` int,
    constraint NAARfk28 foreign key (`NAR1ReasonUpdate`) references ReasonUpdate(reasonUpdateId));


create table AReferralTo(
	aReferralToId int not null auto_increment primary key,
    aReferralTo varchar(255));
insert into AReferralTo(aReferralTo) values ('Another IRCC-funded service');
insert into AReferralTo(aReferralTo) values ('Other');

create table EmploymentStatus(
	employmentStatusId int not null auto_increment primary key,
    employmentStatus varchar(255));
insert into EmploymentStatus(employmentStatus) values ('Unemployed');
insert into EmploymentStatus(employmentStatus) values ('Employed part time - less than 30 hours at main or only job');
insert into EmploymentStatus(employmentStatus) values ('Employed full time - 30 hours or more at main or only job');

create table EducationStatus(
	educationStatusId int not null auto_increment primary key,
    educationStatus varchar(255));
insert into EducationStatus(educationStatus) values ('Full-time student');
insert into EducationStatus(educationStatus) values ('Part-time student');
insert into EducationStatus(educationStatus) values ('Full-time training (e.g. language training or job-specific training)');
insert into EducationStatus(educationStatus) values ('Part-time training (e.g. language training or job-specific training)');
insert into EducationStatus(educationStatus) values ('Not a student');

create table Occupations(
	occupationsId int not null auto_increment primary key,
    occupations varchar(255));
insert into Occupations(occupations) values ('00 Senior management occupations');
insert into Occupations(occupations) values ('01-05 Specialized middle management occupations');
insert into Occupations(occupations) values ('06 Middle management occupations in retail and wholesale trade and customer serv');
insert into Occupations(occupations) values ('07-09 Middle management occupations in trades, transportation, production and ut');
insert into Occupations(occupations) values ('11 Professional occupations in business and finance');
insert into Occupations(occupations) values ('12 Administrative and financial supervisors and administrative occupations');
insert into Occupations(occupations) values ('13 Finance, insurance and related business administrative occupations');
insert into Occupations(occupations) values ('14 Office support occupations');
insert into Occupations(occupations) values ('15 Distribution, tracking and scheduling co-ordination occupations');
insert into Occupations(occupations) values ('21 Professional occupations in natural and applied sciences');
insert into Occupations(occupations) values ('22 Technical occupations related to natural and applied sciences');
insert into Occupations(occupations) values ('30 Professional occupations in nursing');
insert into Occupations(occupations) values ('31 Professional occupations in health (except nursing)');
insert into Occupations(occupations) values ('32 Technical occupations in health');
insert into Occupations(occupations) values ('34 Assisting occupations in support of health services');
insert into Occupations(occupations) values ('40 Professional occupations in education services');
insert into Occupations(occupations) values ('41 Professional occupations in law and social, community and government services');
insert into Occupations(occupations) values ('42 Paraprofessional occupations in legal, social, community and education servic');
insert into Occupations(occupations) values ('43 Occupations in front-line public protection services');
insert into Occupations(occupations) values ('44 Care providers and educational, legal and public protection support occupatio');
insert into Occupations(occupations) values ('51 Professional occupations in art and culture');
insert into Occupations(occupations) values ('52 Technical occupations in art, culture, recreation and sport');
insert into Occupations(occupations) values ('62 Retail sales supervisors and specialized sales occupations');
insert into Occupations(occupations) values ('63 Service supervisors and specialized service occupations');
insert into Occupations(occupations) values ('64 Sales representatives and salespersons - wholesale and retail trade');
insert into Occupations(occupations) values ('65 Service representatives and other customer and personal services occupations');
insert into Occupations(occupations) values ('66 Sales support occupations');
insert into Occupations(occupations) values ('67 Service support and other service occupations, n.e.c.');
insert into Occupations(occupations) values ('72 Industrial, electrical and construction trades');
insert into Occupations(occupations) values ('73 Maintenance and equipment operation trades');
insert into Occupations(occupations) values ('74 Other installers, repairers and servicers and material handlers');
insert into Occupations(occupations) values ('75 Transport and heavy equipment operation and related maintenance occupations');
insert into Occupations(occupations) values ('76 Trades helpers, construction labourers and related occupations');
insert into Occupations(occupations) values ('82 Supervisors and technical occupations in natural resources, agricultural and ');
insert into Occupations(occupations) values ('84 Workers in natural resources, agriculture and related production');
insert into Occupations(occupations) values ('86 Harvesting, landscaping and natural resources labourers');
insert into Occupations(occupations) values ('92 Processing, manufacturing and utilities supervisors and central control opera');
insert into Occupations(occupations) values ('94 Processing and manufacturing machine operators and related production workers');
insert into Occupations(occupations) values ('95 Assemblers in manufacturing');
insert into Occupations(occupations) values ('96 Labourers in processing, manufacturing and utilities');

create table InterventionType(
	interventionTypeId int not null auto_increment primary key,
    interventionType varchar(255));
insert into InterventionType(interventionType) values ('Long Term');
insert into InterventionType(interventionType) values ('Short Term');

create table LongInterventionReceived(
	longTermInterventionReceivedId int not null auto_increment primary key,
    longTermInterventionReceived varchar(255));
insert into LongInterventionReceived(longTermInterventionReceived) values ('Work placement');
insert into LongInterventionReceived(longTermInterventionReceived) values ('Mentoring');
insert into LongInterventionReceived(longTermInterventionReceived) values ('Preparation for licensure/certification');

create table LongStatusOfIntervention(
	statusOfInterventionId int not null auto_increment primary key,
    statusOfIntervention varchar(255));
insert into LongStatusOfIntervention(statusOfIntervention) values ('Intervention ended early (i.e. client ended participation)');
insert into LongStatusOfIntervention(statusOfIntervention) values ('Ongoing');
insert into LongStatusOfIntervention(statusOfIntervention) values ('Completed');

create table LongLeavingIntervention(
	leavingInterventionId int not null auto_increment primary key,
    leavingIntervention varchar(255));
insert into LongLeavingIntervention(leavingIntervention) values ('Found Employment');
insert into LongLeavingIntervention(leavingIntervention) values ('To attend school');
insert into LongLeavingIntervention(leavingIntervention) values ('Medical reason');
insert into LongLeavingIntervention(leavingIntervention) values ('Personal reason');
insert into LongLeavingIntervention(leavingIntervention) values ('Parental leave / caring for family members');
insert into LongLeavingIntervention(leavingIntervention) values ('Lack of support services');
insert into LongLeavingIntervention(leavingIntervention) values ('To receive another settlement service');
insert into LongLeavingIntervention(leavingIntervention) values ('Obtained citizenship');
insert into LongLeavingIntervention(leavingIntervention) values ('Client felt intervention was not meeting current employment needs');
insert into LongLeavingIntervention(leavingIntervention) values ('Moved / unable to contact client');
insert into LongLeavingIntervention(leavingIntervention) values ('Reason unknown');
insert into LongLeavingIntervention(leavingIntervention) values ('Intervention ended by mentor');
insert into LongLeavingIntervention(leavingIntervention) values ('Intervention ended by employer');
insert into LongLeavingIntervention(leavingIntervention) values ('Intervention ended by third party/volunteer');

create table LongSizeEmployerIntervention(
	sizeEmployerInterventionId int not null auto_increment primary key,
    sizeEmployerIntervention varchar(255));
insert into LongSizeEmployerIntervention(sizeEmployerIntervention) values ('Small-medium enterprise - fewer than 500 employees');
insert into LongSizeEmployerIntervention(sizeEmployerIntervention) values ('Large enterprise - 500 employees or more');

create table LongPlacementIntervention(
	placementInterventionId int not null auto_increment primary key,
    placementIntervention varchar(255));
insert into LongPlacementIntervention(placementIntervention) values ('Paid');
insert into LongPlacementIntervention(placementIntervention) values ('Unpaid');

create table LongHoursPerWeekIntervention(
	hoursPerWeekInterventionId int not null auto_increment primary key,
    hoursPerWeekIntervention varchar(255));
insert into LongHoursPerWeekIntervention(hoursPerWeekIntervention) values ('Part-time - less than 30 hours at main or only job');
insert into LongHoursPerWeekIntervention(hoursPerWeekIntervention) values ('Full-time - 30 hours or more at main or only job');

create table LongMetMentorIntervention(
	metMentorInterventionId int not null auto_increment primary key,
    metMentorIntervention varchar(255));
insert into LongMetMentorIntervention(metMentorIntervention) values ('Mentor\'s place of work during work hours');
insert into LongMetMentorIntervention(metMentorIntervention) values ('Other (e.g. coffee shop, restaurant, Skype)');

create table LongHoursMetMentorIntervention(
	hoursMetMentorInterventionId int not null auto_increment primary key,
    hoursMetMentorIntervention varchar(255));
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('1');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('2');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('3');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('4');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('5');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('6');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('7');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('8');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('9');
insert into LongHoursMetMentorIntervention(hoursMetMentorIntervention) values ('10+');

create table LongServicesReceivedIntervention(
	servicesReceivedInterventionId int not null auto_increment primary key,
    servicesReceivedIntervention varchar(255));
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Acupuncturists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Agricultural Equipment Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Agrologists (Agriculture)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Appliance Service Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Architects');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Audiologists & Speech Pathologists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Automotive Painter');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Automotive Service Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Baker');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Boilermaker');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Bricklayer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Cabinetmaker');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Carpenter');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Certified General Accountants');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Certified Management Accountants');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Chartered Accountants');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Chemical Engineer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Chiropractors');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Civil Engineer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Civil Technologists & Technicians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Community Urban Planner');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Concrete Finisher');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Construction Craft Worker');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Construction Electrician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Cook');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Dental Assistants');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Dental Hygienists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Dental Technicians or Technologists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Dentist/Dental Specialist');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Dentists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Denturists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Dieticians/Nutritionists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Drywall Finisher and Plasterer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Electric Motor System Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Electrical & Electronics Engineer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Electrical & Electronics Technologists & Technicians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Elementary School & Kindergarten Teachers');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Embalmers/Funeral Directors');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Floorcovering Installer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Foresters');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Gas Fitter – Class A');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Gas Fitter – Class B');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Geologists/Geoscientists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Glazier');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Hairstylist');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Hearing Aid Practitioners');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Heavy Duty Equipment Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Heavy Equipment Operator');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Home Economists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Hunting Guides');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Industrial & Manufacturing Technologists & Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Industrial Electrician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Industrial Mechanic (Millwright)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Instrumentation and Control Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Insulator (Heat and Frost)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Ironworker (Generalist)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Ironworker (Reinforcing)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Ironworker (Structural/Ornamental)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Land Surveyors');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Landscape Architects');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Landscape Horticulturist');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Lather (Interior Systems Mechanic)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Lawyers');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Licensed Practical Nurses');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Machinist');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Massage Therapist');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Mechanical Engineer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Mechanical Technologists & Technicians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Medical Laboratory Technologists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Medical Radiation Technicians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Metal Fabricator (Fitter)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Midwives');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Mobile Crane Operator');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Mobile Crane Operator (Hydraulic)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Motor Vehicle Body Repairer (Metal and Paint)');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Motorcycle Mechanic');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Naturopathic Physicians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Occupational Therapists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Oil Heat System Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Opticians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Optometrists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Painter and Decorator');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Paramedics');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Partsperson');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Pharmacists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Physicians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Physiotherapists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Plumber');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Podiatrists/Chiropodists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Powerline Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Psychiatric Nurses');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Psychologists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Real Estate Agents');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Recreation Vehicle Service Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Refrigeration and Air Conditioning Mechanic');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Registered Nurses');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Respiratory Therapists');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Rig Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Roofer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Secondary School Teachers');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Sheet Metal Worker');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Social Workers');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Sprinkler System Installer');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Steamfitter/Pipefitter');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Tilesetter');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Tool and Die Maker');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Tower Crane Operator');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Translators');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Transport Trailer Technician');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Truck and Transport Mechanic');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Veterinarians');
insert into LongServicesReceivedIntervention(servicesReceivedIntervention) values ('Welder');

create table ShortInterventionServiceReceived(
	shortInterventionServiceReceivedId int not null auto_increment primary key,
    shortInterventionServiceReceived varchar(255));
insert into ShortInterventionServiceReceived(shortInterventionServiceReceived) values ('Networking opportunities');
insert into ShortInterventionServiceReceived(shortInterventionServiceReceived) values ('Resume screening for referrals service');
insert into ShortInterventionServiceReceived(shortInterventionServiceReceived) values ('Resume added to a matching system');
insert into ShortInterventionServiceReceived(shortInterventionServiceReceived) values ('Employment counselling');


create table TimeSpentMinutes(
	timeSpentMinutesId int not null auto_increment primary key,
    timeSpentMinutes int);
insert into TimeSpentMinutes(timeSpentMinutes) values ('0');
insert into TimeSpentMinutes(timeSpentMinutes) values ('5');
insert into TimeSpentMinutes(timeSpentMinutes) values ('10');
insert into TimeSpentMinutes(timeSpentMinutes) values ('15');
insert into TimeSpentMinutes(timeSpentMinutes) values ('20');
insert into TimeSpentMinutes(timeSpentMinutes) values ('25');
insert into TimeSpentMinutes(timeSpentMinutes) values ('30');
insert into TimeSpentMinutes(timeSpentMinutes) values ('35');
insert into TimeSpentMinutes(timeSpentMinutes) values ('40');
insert into TimeSpentMinutes(timeSpentMinutes) values ('45');
insert into TimeSpentMinutes(timeSpentMinutes) values ('50');
insert into TimeSpentMinutes(timeSpentMinutes) values ('55');

#create table Employment();
create table Employment(
	`templateId` int,
    #constraint Efk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`ER1UpdateRecordID` char(8),
	`ER1UniqueID` int not null,
	constraint Efk2 foreign key (`ER1UniqueID`) references UniqueIdentifier(identifierId),
	`ER1UniqueIDValue` char(8) not null,
    primary key (`templateId`, `ER1UniqueID`, `ER1UniqueIDValue`),
	`ER1DOB` date not null,
	`ER1PostalCode` char(6) not null,
    `ER1RegistrationEI` bool not null,
    `ER1Referral` int not null,
	constraint Efk3 foreign key (`ER1Referral`) references AReferralTo(aReferralToId),
	`ER1LanguageService` int not null,
    constraint Efk4 foreign key (`ER1LanguageService`) references ServiceLanguage(serviceLanguageId),
	`ER1LanguagePreference` int not null,
    constraint Efk5 foreign key (`ER1LanguagePreference`) references LanguagePreference(preferLanguageId),
	`ER1InstitutionType` int not null,
	constraint Efk6 foreign key (`ER1InstitutionType`) references InstitutionType(institutionTypeId),
	`ER1ReferredBy`int not null,
    constraint Efk7 foreign key (`ER1ReferredBy`) references Referrer(referrerId),
    `ER1ReferralDate` date,
    `ER1EmploymentStatus`int not null,
    constraint Efk8 foreign key (`ER1EmploymentStatus`) references EmploymentStatus(employmentStatusId),
	`ER1EducationStatus`int,
    constraint Efk9 foreign key (`ER1EducationStatus`) references EducationStatus(educationStatusId),
    `ER1Occupation`int,
    constraint Efk10 foreign key (`ER1Occupation`) references Occupations(occupationsId),
    `ER1IntendedOccupation`int,
    constraint Efk11 foreign key (`ER1IntendedOccupation`) references Occupations(occupationsId),
    `ER1InterventionType`int,
    constraint Efk12 foreign key (`ER1InterventionType`) references InterventionType(interventionTypeId),
    `ER1LTIInterventionReceived`int,
    constraint Efk13 foreign key (`ER1LTIInterventionReceived`) references LongInterventionReceived(longTermInterventionReceivedId),
	`ER1LTIStatus`int,
    constraint Efk14 foreign key (`ER1LTIStatus`) references LongStatusOfIntervention(statusOfInterventionId),
	`ER1LTIReasonLeaving`int,
    constraint Efk15 foreign key (`ER1LTIReasonLeaving`) references LongLeavingIntervention(leavingInterventionId),
    `ER1LTIEndDate` date,
	`ER1LTISizeEmployer`int,
    constraint Efk16 foreign key (`ER1LTISizeEmployer`) references LongSizeEmployerIntervention(sizeEmployerInterventionId),
	`ER1LTIPlacement`int,
    constraint Efk17 foreign key (`ER1LTIPlacement`) references LongPlacementIntervention(placementInterventionId),
	`ER1LTIHoursPerWeek`int,
    constraint Efk18 foreign key (`ER1LTIHoursPerWeek`) references LongHoursPerWeekIntervention(hoursPerWeekInterventionId),
	`ER1LTIMetMentor`int,
    constraint Efk19 foreign key (`ER1LTIMetMentor`) references LongMetMentorIntervention(metMentorInterventionId),
	`ER1LTIContactMentor`int,
    constraint Efk20 foreign key (`ER1LTIContactMentor`) references LongHoursMetMentorIntervention(hoursMetMentorInterventionId),
	`ER1LTIProfessionTrade`int,
    constraint Efk21 foreign key (`ER1LTIProfessionTrade`) references LongServicesReceivedIntervention(servicesReceivedInterventionId),
    `ER1EssentialSkills` bool,
    `ER1ComputerSkills` bool,
    `ER1DocumentUse` bool,
	`ER1InterpersonalSkills` bool,
    `ER1LeadershipTraining` bool,
    `ER1Numeracy` bool,
	`ER1STIService`int,
    constraint Efk22 foreign key (`ER1STIService`) references ShortInterventionServiceReceived(shortInterventionServiceReceivedId),
	`ER1STIDate` date,
	`ER1SupportServices` bool,
	`ER1CareChildren` bool,
	`ER1C1Age` int,
    constraint Efk23 foreign key (`ER1C1Age`) references Age(ageId),
	`ER1C1TypeCare` int,
    constraint Efk24 foreign key (`ER1C1TypeCare`) references CareType(careTypeId),
	`ER1C2Age` int,
    constraint Efk25 foreign key (`ER1C2Age`) references Age(ageId),
	`ER1C2TypeCare` int,
    constraint Efk26 foreign key (`ER1C2TypeCare`) references CareType(careTypeId),
	`ER1C3Age` int,
    constraint Efk27 foreign key (`ER1C3Age`) references Age(ageId),
	`ER1C3TypeCare` int,
    constraint Efk28 foreign key (`ER1C3TypeCare`)  references CareType(careTypeId),
	`ER1C4Age` int,
    constraint Efk29 foreign key (`ER1C4Age`) references Age(ageId),
	`ER1C4TypeCare` int,
    constraint Efk30 foreign key (`ER1C4TypeCare`)  references CareType(careTypeId),
	`ER1C5Age` int,
    constraint Efk31 foreign key (`ER1C5Age`) references Age(ageId),
	`ER1C5TypeCare` int,
    constraint Efk32 foreign key (`ER1C5TypeCare`) references CareType(careTypeId),
	`ER1Transportation` bool,
	`ER1ProvisionsDisabilities` bool,
	`ER1Translation` bool,
    `ER1TranslationBetween` int,
    constraint Efk33 foreign key (`ER1TranslationBetween`) references ServiceLanguage(serviceLanguageId),
	`ER1TranslationAnd`int,
    constraint Efk34 foreign key (`ER1TranslationAnd`) references ServiceLanguage(serviceLanguageId),
	`ER1Interpretation` bool,
    `ER1InterpretationBetween` int,
    constraint Efk35 foreign key (`ER1InterpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`ER1InterpretationAnd`int,
    constraint Efk36 foreign key (`ER1InterpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`ER1CrisisCounselling` bool,
    `ER1TimeSpendHours` int check (`ER1TimeSpendHours` >= 0 and `ER1TimeSpendHours` <= 500),
    `ER1TimeSpendMinutes` int,
    constraint Efk37 foreign key (`ER1TimeSpendMinutes`) references TimeSpentMinutes(timeSpentMinutesId),
	`ER1UpdateReason` int,
    constraint Efk38 foreign key (`ER1UpdateReason`) references ReasonUpdate(reasonUpdateId));

create table ServicesReceived(
	servicesReceivedId int not null auto_increment primary key,
    servicesReceived varchar(255));
insert into ServicesReceived(servicesReceived) values ('One-on-one orientation');
insert into ServicesReceived(servicesReceived) values ('Family orientation');
insert into ServicesReceived(servicesReceived) values ('Group orientation');

create table LengthOfOrientation(
	lengthOfOrientationId int not null auto_increment primary key,
    lengthOfOrientation varchar(255));
insert into LengthOfOrientation(lengthOfOrientation) values ('5 minutes or less');
insert into LengthOfOrientation(lengthOfOrientation) values ('6 - 30 minutes');
insert into LengthOfOrientation(lengthOfOrientation) values ('30 - 60 minutes');
insert into LengthOfOrientation(lengthOfOrientation) values ('1 to 2 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('2 to 3 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('3 to 4 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('4 to 5 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('5 to 6 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('6 to 7 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('7 to 8 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('8 to 9 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('9 to 10 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('10 to 11 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('11 to 12 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('12 to 13 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('13 to 14 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('14 to 15 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('15 to 16 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('16 to 17 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('17 to 18 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('18 to 19 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('19 to 20 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('20 to 21 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('21 to 22 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('22 to 23 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('23 to 24 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('24 to 25 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('25 to 26 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('26 to 27 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('27 to 28 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('28 to 29 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('29 to 30 hours');
insert into LengthOfOrientation(lengthOfOrientation) values ('Over 30 hours');


create table NumberClientsGroup(
	numberClientsGroupId int not null auto_increment primary key,
    numberClientsGroup varchar(255));
insert into NumberClientsGroup(numberClientsGroup) values ('Less than 10 people');
insert into NumberClientsGroup(numberClientsGroup) values ('10 - 20 people');
insert into NumberClientsGroup(numberClientsGroup) values ('More than 20 people');


#create table InfoAndOrientation();
create table InfoAndOrientation(
	`templateId` int,
    constraint IOfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
        	`IO1UpdateRecordID` char(8),
	`IO1UniqueID` int not null,
	constraint IOfk2 foreign key (`IO1UniqueID`) references UniqueIdentifier(identifierId),
	`IO1UniqueIDValue` char(8) not null,
    primary key (`templateId`, `IO1UniqueID`, `IO1UniqueIDValue`),
	`IO1DOB` date not null,
	`IO1PostalCode` char(6) not null,
	`IO1StartDate` date not null,
	`IO1LanguageService` int not null,
    constraint IOfk3 foreign key (`IO1LanguageService`) references ServiceLanguage(serviceLanguageId),
	`IO1LanguagePreference` int not null,
    constraint IOfk4 foreign key (`IO1LanguagePreference`) references LanguagePreference(preferLanguageId),
	`IO1InstitutionType` int not null,
	constraint IOfk5 foreign key (`IO1InstitutionType`) references InstitutionType(institutionTypeId),
	`IO1ReferredBy`int not null,
	`IO1LengthOrientation` int not null,
    constraint IOfk6 foreign key (`IO1LengthOrientation`) references LengthOfOrientation(lengthOfOrientationId),
    `IO1LengthOrientationHours` int check (`IO1LengthOrientationHours` >= 0 and `IO1LengthOrientationHours` <= 500),
	`IO1LengthOrientationMinutes`int,
    constraint IOfk7 foreign key (`IO1LengthOrientationMinutes`) references TimeSpentMinutes(timeSpentMinutesId),
	`IO1NumberClientsGroup`int,
    constraint IOfk8 foreign key (`IO1NumberClientsGroup`) references NumberClientsGroup(numberClientsGroupId),
    `IO1DirectedSpecificTG` bool,
	`IO1TGChildren` bool,
	`IO1TGYouth` bool,
	`IO1TGSeniors` bool,
	`IO1TGGenderSpecific` bool,
	`IO1TGRefugees` bool,
	`IO1TGCultural` bool,
	`IO1TGDeaf` bool,
	`IO1TGBlind` bool,
	`IO1TGLGBTQ` bool,
	`IO1TGFamilies` bool,
	`IO1TGImpairments` bool,
	`IO1TGITProfession` bool,
	`IO1TGITTrade` bool,
	`IO1TGLanguageMin` bool,
	`IO1OverviewCanada` bool,
	`IO1OverviewCanadaRef` bool,
	`IO1SourcesInformation` bool,
	`IO1SourcesInformationRef` bool,
	`IO1RightsFreedoms` bool,
	`IO1RightsFreedomsRef` int,
	`IO1CanadianLaw` bool,
	`IO1CanadianLawRef` bool,
	`IO1ImportantDocuments` bool,
	`IO1ImportantDocumentsRef` bool,
	`IO1ImprovingLanguage` bool,
	`IO1ImprovingLanguageRef` bool,
	`IO1EmploymentIncome` bool,
	`IO1EmploymentIncomeRef` bool,
	`IO1Education` bool,
	`IO1EducationRef` bool,
	`IO1Housing` bool,
	`IO1HousingRef` bool,
	`IO1Health` bool,
	`IO1HealthRef` bool,
	`IO1MoneyFinance` bool,
	`IO1MoneyFinanceRef` bool,
	`IO1Transportation` bool,
	`IO1TransportationRef` bool,
	`IO1ComMedia` bool,
	`IO1ComMediaRef` bool,
	`IO1ComEngagement` bool,
	`IO1ComEngagementRef` bool,
	`IO1CanadianCitizen` bool,
	`IO1CanadianCitizenRef` bool,
	`IO1InterpersonalConflicts` bool,
	`IO1InterpersonalConflictsRef` bool,
	`IO1EssentialSkills` bool not null,
	`IO1ComputerSkills` bool,
	`IO1DocumentUse` bool,
	`IO1InterpersonalSkills` bool,
	`IO1LeadershipTraining` bool,
	`IO1Numeracy` bool,
	`IO1LifeSkillsService` bool not null,
	`IO1LifeSkills` bool,
	`IO1RightsCitizenship` bool,
	`IO1SupportServices` bool not null,
	`IO1CareChildren` bool,
	`IO1C1Age` int,
    constraint IOfk9 foreign key (`IO1C1Age`) references Age(ageId),
	`IO1C1TypeCare` int,
    constraint IOfk10 foreign key (`IO1C1TypeCare`) references CareType(careTypeId),
	`IO1C2Age` int,
    constraint IOfk11 foreign key (`IO1C2Age`) references Age(ageId),
	`IO1C2TypeCare` int,
    constraint IOfk12 foreign key (`IO1C2TypeCare`) references CareType(careTypeId),
	`IO1C3Age` int,
    constraint IOfk13 foreign key (`IO1C3Age`) references Age(ageId),
	`IO1C3TypeCare` int,
    constraint IOfk14 foreign key (`IO1C3TypeCare`)  references CareType(careTypeId),
	`IO1C4Age` int,
    constraint IOfk15 foreign key (`IO1C4Age`) references Age(ageId),
	`IO1C4TypeCare` int,
    constraint IOfk16 foreign key (`IO1C4TypeCare`)  references CareType(careTypeId),
	`IO1C5Age` int,
    constraint IOfk17 foreign key (`IO1C5Age`) references Age(ageId),
	`IO1C5TypeCare` int,
    constraint IOfk18 foreign key (`IO1C5TypeCare`) references CareType(careTypeId),
	`IO2Transportation` bool,
	`IO1ProvisionsDisabilities` bool,
	`IO1Translation` bool,
    `IO1TranslationBetween` int,
    constraint IOfk19 foreign key (`IO1TranslationBetween`) references ServiceLanguage(serviceLanguageId),
	`IO1TranslationAnd`int,
    constraint IOfk20 foreign key (`IO1TranslationAnd`) references ServiceLanguage(serviceLanguageId),
	`IO1Interpretation` bool,
    `IO1InterpretationBetween` int,
    constraint IOfk21 foreign key (`IO1InterpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`IO1InterpretationAnd`int,
    constraint IOfk22 foreign key (`IO1InterpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`IO1CrisisCounselling` bool,
	`IO1EndDateService` date not null,
	`IO1UpdateReason` int,
    constraint IOfk23 foreign key (`IO1UpdateReason`) references ReasonUpdate(reasonUpdateId));

create table ClientServiceActivity(
	clientServiceActivityId int not null auto_increment primary key,
  clientServiceActivity varchar(255) not null);

insert into ClientServiceActivity(clientServiceActivity) values ('Community-based group events and activities');
insert into ClientServiceActivity(clientServiceActivity) values ('Targeted Matching and Networking');

create table EventsAttended(
	eventId int not null auto_increment primary key,
	eventType varchar(255) not null);

insert into EventsAttended(eventType) values ('Events/visits pertaining to culture or history');
insert into EventsAttended(eventType) values ('Field trip connecting newcomer to community resources or local services');
insert into EventsAttended(eventType) values ('Sports/recreation event');
insert into EventsAttended(eventType) values ('Neighbourhood day');
insert into EventsAttended(eventType) values ('Other community event');

create table ServiceTypes(
	serviceTypeId int not null auto_increment primary key,
	serviceType varchar(255) not null);

insert into ServiceTypes(serviceType) values ('Conversation circle');
insert into ServiceTypes(serviceType) values ('Targeted matching between newcomer and settled immigrant or long-time Canadian');
insert into ServiceTypes(serviceType) values ('Networking activity with other newcomers or Canadian citizens');
insert into ServiceTypes(serviceType) values ('Youth leadership project');
insert into ServiceTypes(serviceType) values ('Other regular group activities to address ongoing needs or interests');

create table ServiceTopics(
	serviceTopicId int not null auto_increment primary key,
	serviceTopic varchar(255) not null);

insert into ServiceTopics(serviceTopic) values ('Access to local community services');
insert into ServiceTopics(serviceTopic) values ('Connecting with other newcomers');
insert into ServiceTopics(serviceTopic) values ('Getting involved in the community');
insert into ServiceTopics(serviceTopic) values ('Targeted Matching and Networking: Accessing the labour market');
insert into ServiceTopics(serviceTopic) values ('Connecting with settled immigrants or long-time Canadians');
insert into ServiceTopics(serviceTopic) values ('Community-based group events and activities: Increasing knowledge of Canadian society/context');
insert into ServiceTopics(serviceTopic) values ('Targeted Matching and Networking: Informal problem solving');
insert into ServiceTopics(serviceTopic) values ('Inter-cultural sensitivity and understanding (e.g. interpersonal dynamics)');
insert into ServiceTopics(serviceTopic) values ('Targeted Matching and Networking: Language learning');

create table ServicesOffered(
	serviceId int not null auto_increment primary key,
	serviceDescription varchar(255) not null);

insert into ServicesOffered(serviceDescription) values ('Community-based group events and activities: Group session (e.g. conversation circles)');
insert into ServicesOffered(serviceDescription) values ('Targeted Matching and Networking: One-on-one session (e.g. mentoring)');
insert into ServicesOffered(serviceDescription) values ('Targeted Matching and Networking: Group session (e.g. conversation circles)');
insert into ServicesOffered(serviceDescription) values ('One-on-one orientation');
insert into ServicesOffered(serviceDescription) values ('Family orientation');
insert into ServicesOffered(serviceDescription) values ('Group orientation');

create table UniqueParticipantSizes(
	participantsizeId int not null auto_increment primary key,
	participantsGroupSize varchar(255) not null);

insert into UniqueParticipantSizes(participantsGroupSize) values ('Less than 10 people');
insert into UniqueParticipantSizes(participantsGroupSize) values ('10 - 20 people');
insert into UniqueParticipantSizes(participantsGroupSize) values ('More than 20 people');

create table ServiceStatus(
	serviceStatusId int not null auto_increment primary key,
	serviceStatus varchar(255) not null);

insert into ServiceStatus(serviceStatus) values ('Service ended early (i.e. client ended participation)');
insert into ServiceStatus(serviceStatus) values ('Ongoing');
insert into ServiceStatus(serviceStatus) values ('Completed');

create table ServiceAbortReason(
	serviceAbortReasonId int not null auto_increment primary key,
	serviceAbortReason varchar(255) not null);

insert into ServiceAbortReason(serviceAbortReason) values ('Client felt the service was not meeting current needs');
insert into ServiceAbortReason(serviceAbortReason) values ('Found employment');
insert into ServiceAbortReason(serviceAbortReason) values ('Lack of support services');
insert into ServiceAbortReason(serviceAbortReason) values ('Moved/unable to contact client');
insert into ServiceAbortReason(serviceAbortReason) values ('Obtained citizenship');
insert into ServiceAbortReason(serviceAbortReason) values ('Medical reason');
insert into ServiceAbortReason(serviceAbortReason) values ('Personal reason');
insert into ServiceAbortReason(serviceAbortReason) values ('Parental leave/caring for family members');
insert into ServiceAbortReason(serviceAbortReason) values ('Student left school');
insert into ServiceAbortReason(serviceAbortReason) values ('To attend school');
insert into ServiceAbortReason(serviceAbortReason) values ('To receive another settlement service');
insert into ServiceAbortReason(serviceAbortReason) values ('unknown');
insert into ServiceAbortReason(serviceAbortReason) values ('Intervention ended by mentor');
insert into ServiceAbortReason(serviceAbortReason) values ('Intervention ended by third party/volunteer');

create table CommunityConnections(
	`templateId` int,
    constraint CCfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`CC1UpdateRecordID` char(8),
	`CC1UniqueID` int not null,
	constraint CCfk2 foreign key (`CC1UniqueID`) references UniqueIdentifier(identifierId),
	`CC1UniqueIDVal` char(8) not null,
    primary key (`templateId`, `CC1UniqueID`, `CC1UniqueIDVal`),
	`CC1DOB` date not null,
	`CC1PostalCode` char(6) not null,
	`CC1ServiceLanguage` int not null,
	constraint CCfk4 foreign key (`CC1ServiceLanguage`) references ServiceLanguage(serviceLanguageId),
	`CC1OfficialLanguagePrefered` int not null,
	constraint CCfk5 foreign key (`CC1OfficialLanguagePrefered`) references LanguagePreference(preferLanguageId),
	`CC1ReferredBy`int not null,
  constraint CCfk6 foreign key (`CC1ReferredBy`) references Referrer(referrerId),
	`CC1ClientServiceActivity` int not null,
	constraint CCfk7 foreign key (`CC1ClientServiceActivity`) references ClientServiceActivity(clientServiceActivityId),
	`CC1InstitutionType` int not null,
	constraint CCfk8 foreign key (`CC1InstitutionType`) references InstitutionType(institutionTypeId),
	`CC1TypeofEventAttended` int,
	constraint CCfk9 foreign key (`CC1TypeofEventAttended`) references EventsAttended(eventId),
	`CC1TypeofService` int,
	constraint CCfk10 foreign key (`CC1TypeofService`) references ServiceTypes(serviceTypeId),
	`CC1MainTopicOfServices` int not null,
	constraint CCfk11 foreign key (`CC1MainTopicOfServices`) references ServiceTopics(serviceTopicId),
	`CC1ServiceReceived` int not null,
	constraint CCfk12 foreign key (`CC1ServiceReceived`) references ServicesOffered(serviceId),
	`CC1NumOfUniqueParticipants` int,
	constraint CCfk13 foreign key (`CC1NumOfUniqueParticipants`) references UniqueParticipantSizes(participantsizeId),
	`CC1VolunteerParticipated` bool,
	`CC1SpecificTargetGroup` bool,
	`CC1TG_Children` bool,
	`CC1TG_Youth` bool,
	`CC1TG_Senior` bool,
	`CC1TG_Gender` bool,
	`CC1TG_Refugees` bool,
	`CC1TG_Ethnic` bool,
	`CC1TG_DeaforHardofHearing` bool,
	`CC1TG_BlindorPartiallySighted` bool,
	`CC1TG_LGBTQ` bool,
	`CC1TG_Families` bool,
	`CC1TG_Otherimpairments` bool,
	`CC1TG_ClientsWithInternationalTraining_RegulatedProfession` bool,
	`CC1TG_ClientswithInternationalTraining_RegulatedTrade` bool,
	`CC1TG_OfficialLanguageMinorities` bool,
	`CC1StatusofService` int not null,
	constraint CCfk14 foreign key (`CC1StatusofService`) references ServiceStatus(serviceStatusId),
	`CC1ReasonforLeavingService` int,
	constraint CCfk15 foreign key (`CC1ReasonforLeavingService`) references ServiceAbortReason(serviceAbortReasonId),
	`CC1StartDate` date not null,
	`CC1EndDate` date not null,
	`CC1ProjectedEndDate` date not null,
	`CC1EssentialSkills` bool not null,
	`CC1ComputerSkills` bool,
	`CC1DocumentUse` bool,
	`CC1InterpersonalSkills` bool,
	`CC1LeadershipTraining` bool,
	`CC1LifeSkills` bool,
	`CC1Numeracy` bool,
	`CC1SupportServicesReceived` bool not null,
	`CC1CareforChildren` bool,
	`CC1Child1Age` int,
	constraint CCfk16 foreign key (`CC1Child1Age`) references Age(ageId),
	`CC1Child1TypeofCare` int,
	constraint CCfk17 foreign key (`CC1Child1TypeofCare`) references CareType(careTypeId),
	`CC1Child2Age` int,
	constraint CCfk18 foreign key (`CC1Child2Age`) references Age(ageId),
	`CC1Child2TypeofCare` int,
    constraint CCfk19 foreign key (`CC1Child2TypeofCare`) references CareType(careTypeId),
	`CC1Child3Age` int,
  constraint CCfk20 foreign key (`CC1Child3Age`) references Age(ageId),
	`CC1Child3TypeofCare` int,
  constraint CCfk21 foreign key (`CC1Child3TypeofCare`) references CareType(careTypeId),
	`CC1Child4Age` int,
  constraint CCfk22 foreign key (`CC1Child4Age`) references Age(ageId),
	`CC1Child4TypeofCare` int,
  constraint CCfk23 foreign key (`CC1Child4TypeofCare`) references CareType(careTypeId),
  `CC1Child5Age` int,
  constraint CCfk24 foreign key (`CC1Child5Age`) references Age(ageId),
	`CC1Child5TypeofCare` int,
  constraint CCfk25 foreign key (`CC1Child5TypeofCare`) references CareType(careTypeId),
	`CC1Transportation` bool,
	`CC1ProvisionsforDisabilities` bool,
	`CC1Translation` bool,
	`CC1TranslationBetween` int not null,
  constraint CCfk26 foreign key (`CC1TranslationBetween`) references ServiceLanguage(serviceLanguageId),
	`CC1TranslationAnd` int not null,
  constraint CCfk27 foreign key (`CC1TranslationAnd`) references ServiceLanguage(serviceLanguageId),
	`CC1Interpretation` bool,
  `CC1InterpretationBetween` int not null,
  constraint language foreign key (`CC1InterpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`CC1InterpretationAnd` int not null,
  constraint CCfk28 foreign key (`CC1InterpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`CC1CrisisCounselling` bool,
	`CC1LengthofServiceHours` int,
	`CC1LengthofServiceMinutes` int,
	`CC1Reasonforupdate` int,
  constraint CCfk29 foreign key (`CC1Reasonforupdate`) references ReasonUpdate(reasonUpdateId));

create table LTClientEnrolment(
	`templateId` int,
    constraint LTCEfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`LTCE1UpdaterecordID` char(8),
	`LTCE1UniqueIDType` int not null,
	constraint LTCEfk2 foreign key (`LTCE1UniqueIDType`) references UniqueIdentifier(identifierId),
	`LTCE1UniqueIDValue` char(8) not null,
    primary key (`templateId`, `LTCE1UniqueIDType`, `LTCE1UniqueIDValue`),
	`LTCE1DOB` date not null,
	`LTCE1PostalCode` char(6) not null,
	`LTCE1CourseCode` varchar(255) not null,
	`LTCE1DateOfFistClass` date not null,
	`LTCE1OfficialLanguagePrefered` int not null,
	constraint LTCEfk3 foreign key (`LTCE1OfficialLanguagePrefered`) references LanguagePreference(preferLanguageId),
	`LTCE1SupportServicesReceived` bool not null,
	`LTCE1CareforChildren` bool,
	`LTCE1Child1Age` int,
	constraint LTCEfk4 foreign key (`LTCE1Child1Age`) references Age(ageId),
	`LTCE1Child1TypeofCare` int,
	constraint LTCEfk5 foreign key (`LTCE1Child1TypeofCare`) references CareType(careTypeId),
	`LTCE1Child2Age` int,
	constraint LTCEfk6 foreign key (`LTCE1Child2Age`) references Age(ageId),
	`LTCE1Child2TypeofCare` int,
    constraint LTCEfk7 foreign key (`LTCE1Child2TypeofCare`) references CareType(careTypeId),
	`LTCE1Child3Age` int,
    constraint LTCEfk8 foreign key (`LTCE1Child3Age`) references Age(ageId),
	`LTCE1Child3TypeofCare` int,
    constraint LTCEfk9 foreign key (`LTCE1Child3TypeofCare`) references CareType(careTypeId),
	`LTCE1Child4Age` int,
    constraint LTCEfk10 foreign key (`LTCE1Child4Age`) references Age(ageId),
	`LTCE1Child4TypeofCare` int,
    constraint LTCEfk11 foreign key (`LTCE1Child4TypeofCare`) references CareType(careTypeId),
    `LTCE1Child5Age` int,
    constraint LTCEfk12 foreign key (`LTCE1Child5Age`) references Age(ageId),
	`LTCE1Child5TypeofCare` int,
  constraint LTCEfk13 foreign key (`LTCE1Child5TypeofCare`) references CareType(careTypeId),
	`LTCE1Transportation` bool,
	`LTCE1ProvisionsforDisabilities` bool,
	`LTCE1Translation` bool,
	`LTCE1TranslationBetween` int not null,
  constraint LTCEfk14 foreign key (`LTCE1TranslationBetween`) references ServiceLanguage(serviceLanguageId),
	`LTCE1TranslationAnd` int not null,
  constraint LTCEfk15 foreign key (`LTCE1TranslationAnd`) references ServiceLanguage(serviceLanguageId),
	`LTCE1Interpretation` bool,
  `LTCE1InterpretationBetween` int not null,
  constraint LTCEfk16 foreign key (`LTCE1InterpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`LTCE1InterpretationAnd` int not null,
  constraint LTCEfk17 foreign key (`LTCE1InterpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`LTCE1CrisisCounselling` bool,
	`LTCE1Reasonforupdate` int,
  constraint LTCEfk18 foreign key (`LTCE1Reasonforupdate`) references ReasonUpdate(reasonUpdateId));

create table FormatOfTrainingProvided(
	trainingProvidedId int not null auto_increment primary key,
    trainingProvided varchar(255));

insert into FormatOfTrainingProvided(trainingProvided) values ('Blended classroom and online training');
insert into FormatOfTrainingProvided(trainingProvided) values ('Classroom');
insert into FormatOfTrainingProvided(trainingProvided) values ('Itinerant teachers');
insert into FormatOfTrainingProvided(trainingProvided) values ('One-on-one tutoring');
insert into FormatOfTrainingProvided(trainingProvided) values ('Other distance learning (e.g. videoconferencing)');
insert into FormatOfTrainingProvided(trainingProvided) values ('Online computer training');
insert into FormatOfTrainingProvided(trainingProvided) values ('Workplace');

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

create table LTCourseSetup(
	`templateId` int,
    constraint LTCS1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`processingDetails` varchar(255),
	`LTS1updateRecordID` int(10),
	`LTS1courseCode` varchar(25) not null,
    primary key (`templateId`, `LTS1courseCode`),
	`LTS1notes` varchar(255),
	`LTS1courseHeldOnAnOngoingBasis` bool not null,
	`LTS1officialLanguageOfCourse` int not null, 
    constraint LTCS2 foreign key (`LTS1officialLanguageOfCourse`) references ServiceLanguage(serviceLanguageId),
	`LTS1formatOfTrainingProvided` int not null,
    constraint LTCS3 foreign key (`LTS1formatOfTrainingProvided`) references FormatOfTrainingProvided(trainingProvidedId),
	`LTS1classesHeldAt` varchar(255),
	`LTS1inPersonInstruction(%)` decimal(5, 2) check (`LTS1inPersonInstruction(%)` > 0 and `LTS1inPersonInstruction(%)` <= 100),
	`LTS1onlineDistanceInstruction(%)` decimal(5, 2) check (`LTS1onlineDistanceInstruction(%)` > 0 and `LTS1onlineDistanceInstruction(%)` <= 100),
	`LTS1totalNumberofSpotsinCourse` int not null,
	`LTS1numberOfIRCCFundedSpotsinCourse` int not null,
	`LTS1newStudentsCanEnrolInTheCourse` int not null,
    constraint LTCS4 foreign key (`LTS1newStudentsCanEnrolInTheCourse`) references NewEnrollmentInCourseFrequency(frequencyId),
	`LTS1supportServicesAvailableForClientInThisCourse` bool not null,
	`LTS1careForNewcomerChildren` bool,
	`LTS1transportation` bool,
	`LTS1provisionsForDisabilities` bool,
	`LTS1courseStartDate` date not null,
	`LTS1courseEndDate` date not null,
	`LTS1scheduleMorning` bool,
	`LTS1scheduleAfternoon` bool,
	`LTS1scheduleEvening` bool,
	`LTS1scheduleWeekend` bool,
	`LTS1scheduleAnytime` bool,
	`LTS1scheduleOnline` bool,
	`LTS1instructionalHoursPerClass` decimal(5, 2) not null,
	`LTS1classesPerWeek` int not null,
	`LTS1weeksOfInstruction` int,
	`LTS1weeksOfInstructionPerYear` int,
	`LTS1dominantFocusOfTheCourse` varchar(255) not null,
	`LTS1courseDirectedAtASpecificTargetGroup` bool not null,
	`LTS1children (0-14 yrs)` bool,
	`LTS1youth (15-24 yrs)` bool,
	`LTS1senior` bool,
	`LTS1genderSpecific` bool,
	`LTS1refugees` bool,
	`LTS1ethnicCulturalLinguisticGroup` bool,
	`LTS1officialLanguageMinorities` bool,
	`LTS1deafOrHardOfHearing` bool,
	`LTS1blindOrPartiallySighted` bool,
	`LTS1clientsWithOtherImpairments (physical, mental)` bool,
	`LTS1lesbianGayBisexualTransgenderQueer (LGBTQ)` bool,
	`LTS1familiesParents` bool,
	`LTS1clientsWithInternationalTrainingInARegulatedProfession` bool,
	`LTS1clientsWithInternationalTrainingInARegulatedTrade` bool,
	`LTS1materialsUsedInCourse` bool not null,
	`LTS1citizenshipPreparation` bool,
	`LTS1PBLALanguageCompanion` bool,
	`LTS1contactName` varchar(255),
	`LTS1streetNumber` int not null,
	`LTS1streetName` varchar(255) not null,
	`LTS1streetType` int not null,
    constraint LTCS5 foreign key (`LTS1streetType`) references StreetType(streetTypeId),
	`LTS1streetDirection` int,
    constraint LTCS6 foreign key (`LTS1streetDirection`) references StreetDirection(streetDirectionId),
	`LTS1unitSuite` int,
	`LTS1province` varchar(255) not null,
	`LTS1city` varchar(255) not null,
	`LTS1postalCode` char(6) not null,
	`LTS1telephoneNumber` int(20) not null,
	`LTS1telephoneExtension` int(3),
	`LTS1emailAddress` varchar(255) not null,
	`LTS1listeningSkillLevel1` int,
	`LTS1listeningSkillLevel2` int,
	`LTS1listeningSkillLevel3` int,
	`LTS1listeningSkillLevel4` int,
	`LTS1listeningSkillLevel5` int,
	`LTS1listeningSkillLevel6` int,
	`LTS1listeningSkillLevel7` int,
	`LTS1listeningSkillLevel8` int,
	`LTS1listeningSkillLevel9` int,
	`LTS1listeningSkillLevel10` int,
	`LTS1listeningSkillLevel11` int,
	`LTS1listeningSkillLevel12` int,
	`LTS1speakingSkillLevel1` int,
	`LTS1speakingSkillLevel2` int,
	`LTS1speakingSkillLevel3` int,
	`LTS1speakingSkillLevel4` int,
	`LTS1speakingSkillLevel5` int,
	`LTS1speakingSkillLevel6` int,
	`LTS1speakingSkillLevel7` int,
	`LTS1speakingSkillLevel8` int,
	`LTS1speakingSkillLevel9` int,
	`LTS1speakingSkillLevel10` int,
	`LTS1speakingSkillLevel11` int,
	`LTS1speakingSkillLevel12` int,
	`LTS1readingSkillLevel1` int,
	`LTS1readingSkillLevel2` int,
	`LTS1readingSkillLevel3` int,
	`LTS1readingSkillLevel4` int,
	`LTS1readingSkillLevel5` int,
	`LTS1readingSkillLevel6` int,
	`LTS1readingSkillLevel7` int,
	`LTS1readingSkillLevel8` int,
	`LTS1readingSkillLevel9` int,
	`LTS1readingSkillLevel10` int,
	`LTS1readingSkillLevel11` int,
	`LTS1readingSkillLevel12` int,
	`LTS1readingSkillLevel13` int,
	`LTS1readingSkillLevel14` int,
	`LTS1readingSkillLevel15` int,
	`LTS1readingSkillLevel16` int,
	`LTS1readingSkillLevel17` int,
	`LTS1writingSkillLevel1` int,
	`LTS1writingSkillLevel2` int,
	`LTS1writingSkillLevel3` int,
	`LTS1writingSkillLevel4` int,
	`LTS1writingSkillLevel5` int,
	`LTS1writingSkillLevel6` int,
	`LTS1writingSkillLevel7` int,
	`LTS1writingSkillLevel8` int,
	`LTS1writingSkillLevel9` int,
	`LTS1writingSkillLevel10` int,
	`LTS1writingSkillLevel11` int,
	`LTS1writingSkillLevel12` int,
	`LTS1writingSkillLevel13` int,
	`LTS1writingSkillLevel14` int,
	`LTS1writingSkillLevel15` int,
	`LTS1writingSkillLevel16` int,
	`LTS1writingSkillLevel17` int);

create table LTClientExit(
	`templateId` int,
    constraint LTCE1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`LTE1processingDetails` varchar(255),
	`LTE1updateRecordID` int(10),
	`LTE1uniqueIdentifierType` int not null,
    constraint LTCE0 foreign key (`LTE1uniqueIdentifierType`) references UniqueIdentifier(identifierId),
	`LTE1uniqueIdentifierValue` int(10) not null,
    primary key (`templateId`,`LTE1uniqueIdentifierType`,`LTE1uniqueIdentifierValue`),
	`LTE1clientDateOfBirth` date not null,
	`LTE1courseCode` varchar(255) not null,
	`LTE1clientTrainingStatus` varchar(255) not null,
	`LTE1dateClientExitedCourse` date,
	`LTE1reasonForExitingCourse` varchar(255),
	`LTE1listeningCLBLevel` int,
	`LTE1speakingCLBLevel` int,
	`LTE1readingCLBLevel` int,
	`LTE1writingCLBLevel` int,
	`LTE1wasACertificateIssuedToTheClient` bool not null,
	`LTE1listeningLevelIndicatedOnCertificate` int,
	`LTE1speakingLevelIndicatedOnCertificate` int,
	`LTE1supportServicesReceived` bool not null,
	`LTE1careForNewcomerChildren` bool,
	`LTE1child1Age` int,
    constraint LTCE2 foreign key (`LTE1child1Age`) references Age(ageId),
	`LTE1child1TypeOfCare` int,
    constraint LTCE3 foreign key (`LTE1child1TypeOfCare`) references CareType(careTypeId),
	`LTE1child2Age` int,
	constraint LTCE4 foreign key (`LTE1child2Age`) references Age(ageId),
	`LTE1child2TypeOfCare` int,
    constraint LTCE5 foreign key (`LTE1child2TypeOfCare`) references CareType(careTypeId),
	`LTE1child3Age` int,
	constraint LTCE6 foreign key (`LTE1child3Age`) references Age(ageId),
	`LTE1child3TypeOfCare` int,
    constraint LTCE7 foreign key (`LTE1child3TypeOfCare`) references CareType(careTypeId),
	`LTE1child4Age` int,
	constraint LTCE8 foreign key (`LTE1child4Age`) references Age(ageId),
	`LTE1child4TypeOfCare` int,
    constraint LTCE9 foreign key (`LTE1child4TypeOfCare`) references CareType(careTypeId),
	`LTE1child5Age` int,
	constraint LTCE10 foreign key (`LTE1child5Age`) references Age(ageId),
	`LTE1child5TypeOfCare` int,
    constraint LTCE11 foreign key (`LTE1child5TypeOfCare`) references CareType(careTypeId),
	`LTE1transportation` bool,
	`LTE1provisionsForDisabilities` bool,
	`LTE1translation` bool,
	`LTE1translationLanguageBetween` int,
    constraint LTCE12 foreign key (`LTE1translationLanguageBetween`) references ServiceLanguage(serviceLanguageId),
	`LTE1translationLanguageAnd` int,
    constraint LTCE13 foreign key (`LTE1translationLanguageAnd`) references ServiceLanguage(serviceLanguageId),
	`LTE1interpretation` bool,
	`LTE1interpretationBetween`int,
    constraint LTCE14 foreign key (`LTE1interpretationBetween`) references ServiceLanguage(serviceLanguageId),
	`LTE1interpretationAnd` int,
    constraint LTCE15 foreign key (`LTE1interpretationAnd`) references ServiceLanguage(serviceLanguageId),
	`LTE1crisisCounselling` bool,
	`LTE1reasonForUpdate` varchar(255)
);

create table VariableNameVariableName(
	variableName varchar(255) primary key,
    realName varchar(255) not null);

#Insert column names of Employment - ER
insert into VariableName(variableName, realName) values ('ER1UpdateRecordID', 'Update Record ID');
insert into VariableName(variableName, realName) values ('ER1UniqueID', 'Unique Identifier');
insert into VariableName(variableName, realName) values ('ER1UniqueIDValue', 'Unique Identifier Value');
insert into VariableName(variableName, realName) values ('ER1DOB', 'Date of Birth (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('ER1PostalCode', 'Postal Code where the service was received');
insert into VariableName(variableName, realName) values ('ER1RegistrationEI', 'Registration in an employment intervention');
insert into VariableName(variableName, realName) values ('ER1Referral', 'A referral to');
insert into VariableName(variableName, realName) values ('ER1LanguageService', 'Language of Service');
insert into VariableName(variableName, realName) values ('ER1LanguagePreference', 'Official Language of Preference');
insert into VariableName(variableName, realName) values ('ER1InstitutionType', 'Type of Institution/Organization Where Client Received Services');
insert into VariableName(variableName, realName) values ('ER1ReferredBy', 'Referred By');
insert into VariableName(variableName, realName) values ('ER1ReferralDate', 'Referral Date (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('ER1EmploymentStatus', 'Employment Status');
insert into VariableName(variableName, realName) values ('ER1EducationStatus', 'Education Status');
insert into VariableName(variableName, realName) values ('ER1Occupation', 'Occupation in Canada');
insert into VariableName(variableName, realName) values ('ER1IntendedOccupation', 'Intended Occupation');
insert into VariableName(variableName, realName) values ('ER1InterventionType', 'Intervention Type');
insert into VariableName(variableName, realName) values ('ER1LTIInterventionReceived', 'Long Term Intervention: Intervention Received');
insert into VariableName(variableName, realName) values ('ER1LTIStatus', 'Long Term Intervention: Status of Intervention');
insert into VariableName(variableName, realName) values ('ER1LTIReasonLeaving', 'Long Term Intervention: Reason For Leaving Intervention');
insert into VariableName(variableName, realName) values ('ER1LTIEndDate', 'Long Term Intervention: End Date (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('ER1LTISizeEmployer', 'Long Term Intervention: Size of Employer');
insert into VariableName(variableName, realName) values ('ER1LTIPlacement', 'Long Term Intervention: Placement Was');
insert into VariableName(variableName, realName) values ('ER1LTIHoursPerWeek', 'Long Term Intervention: Hours Per Week');
insert into VariableName(variableName, realName) values ('ER1LTIMetMentor', 'Long Term Intervention: Client Met Mentor Regularly at');
insert into VariableName(variableName, realName) values ('ER1LTIContactMentor', 'Long Term Intervention: Average Hours/Week in Contact with Mentor');
insert into VariableName(variableName, realName) values ('ER1LTIProfessionTrade', 'Long Term Intervention: Profession/Trade For Which Services Were Received');
insert into VariableName(variableName, realName) values ('ER1EssentialSkills', 'Was Essential Skills and Aptitude Training Received as Part of this Service?');
insert into VariableName(variableName, realName) values ('ER1ComputerSkills', 'Computer skills');
insert into VariableName(variableName, realName) values ('ER1DocumentUse', 'Document Use');
insert into VariableName(variableName, realName) values ('ER1InterpersonalSkills', 'Interpersonal Skills and Workplace Culture');
insert into VariableName(variableName, realName) values ('ER1LeadershipTraining', 'Leadership Training');
insert into VariableName(variableName, realName) values ('ER1Numeracy', 'Numeracy');
insert into VariableName(variableName, realName) values ('ER1STIService', 'Short Term Intervention: Service Received');
insert into VariableName(variableName, realName) values ('ER1STIDate', 'Short Term Intervention: Date (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('ER1SupportServices', 'Support Services Received');
insert into VariableName(variableName, realName) values ('ER1CareChildren', 'Care for Newcomer Children');
insert into VariableName(variableName, realName) values ('ER1C1Age', 'Child 1: Age');
insert into VariableName(variableName, realName) values ('ER1C1TypeCare', 'Child 1: Type of Care');
insert into VariableName(variableName, realName) values ('ER1C2Age', 'Child 2: Age');
insert into VariableName(variableName, realName) values ('ER1C2TypeCare', 'Child 2: Type of Care');
insert into VariableName(variableName, realName) values ('ER1C3Age', 'Child 3: Age');
insert into VariableName(variableName, realName) values ('ER1C3TypeCare', 'Child 3: Type of Care');
insert into VariableName(variableName, realName) values ('ER1C4Age', 'Child 4: Age');
insert into VariableName(variableName, realName) values ('ER1C4TypeCare', 'Child 4: Type of Care');
insert into VariableName(variableName, realName) values ('ER1C5Age', 'Child 5: Age');
insert into VariableName(variableName, realName) values ('ER1C5TypeCare', 'Child 5: Type of Care');
insert into VariableName(variableName, realName) values ('ER1Transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('ER1ProvisionsDisabilities', 'Provisions for Disabilities');
insert into VariableName(variableName, realName) values ('ER1Translation', 'Translation');
insert into VariableName(variableName, realName) values ('ER1TranslationBetween', 'Translation: Between');
insert into VariableName(variableName, realName) values ('ER1TranslationAnd', 'Translation: And');
insert into VariableName(variableName, realName) values ('ER1Interpretation', 'Interpretation');
insert into VariableName(variableName, realName) values ('ER1InterpretationBetween', 'Interpretation: Between');
insert into VariableName(variableName, realName) values ('ER1InterpretationAnd', 'Interpretation: And');
insert into VariableName(variableName, realName) values ('ER1CrisisCounselling', 'Crisis Counselling');
insert into VariableName(variableName, realName) values ('ER1TimeSpendHours', 'Time Spent With Client/Addressing Client\'s Employment Needs: Hours');
insert into VariableName(variableName, realName) values ('ER1TimeSpendMinutes', 'Time Spent With Client/Addressing Client\'s Employment Needs: Minutes');
insert into VariableName(variableName, realName) values ('ER1UpdateReason', 'Reason for update');


#Insert column names of Info and Orientation - IO
insert into VariableName(variableName, realName) values ('IO1UpdateRecordID', 'Update Record ID');
insert into VariableName(variableName, realName) values ('IO1UniqueID', 'Unique Identifier');
insert into VariableName(variableName, realName) values ('IO1UniqueIDValue', 'Unique Identifier Value');
insert into VariableName(variableName, realName) values ('IO1DOB', 'Date of Birth (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('IO1PostalCode', 'Postal Code where the service was received');
insert into VariableName(variableName, realName) values ('IO1StartDate', 'Start Date of Service (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('IO1LanguageService', 'Language of Service');
insert into VariableName(variableName, realName) values ('IO1LanguagePreference', 'Official Language of Preference');
insert into VariableName(variableName, realName) values ('IO1InstitutionType', 'Type of Institution/Organization Where Client Received Services');
insert into VariableName(variableName, realName) values ('IO1ReferredBy', 'Referred By');
insert into VariableName(variableName, realName) values ('IO1LengthOrientation', 'Total Length of Orientation');
insert into VariableName(variableName, realName) values ('IO1LengthOrientationHours', 'Total Length of Orientation: Hours');
insert into VariableName(variableName, realName) values ('IO1LengthOrientationMinutes', 'Total Length of Orientation: Minutes');
insert into VariableName(variableName, realName) values ('IO1NumberClientsGroup', 'Number of Clients in Group');
insert into VariableName(variableName, realName) values ('IO1DirectedSpecificTG', 'Directed at a specific Target Group');
insert into VariableName(variableName, realName) values ('IO1TGChildren', 'Target Group: Children (0-14 yrs)');
insert into VariableName(variableName, realName) values ('IO1TGYouth', 'Target Group: Youth (15-24 yrs)');
insert into VariableName(variableName, realName) values ('IO1TGSeniors', 'Target Group: Seniors');
insert into VariableName(variableName, realName) values ('IO1TGGenderSpecific', 'Target Group: Gender-specific');
insert into VariableName(variableName, realName) values ('IO1TGRefugees', 'Target Group: Refugees');
insert into VariableName(variableName, realName) values ('IO1TGCultural', 'Target Group: Ethnic/cultural/linguistic group');
insert into VariableName(variableName, realName) values ('IO1TGDeaf', 'Target Group: Deaf or Hard of Hearing');
insert into VariableName(variableName, realName) values ('IO1TGBlind', 'Target Group: Blind or Partially Sighted');
insert into VariableName(variableName, realName) values ('IO1TGLGBTQ', 'Target Group: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)');
insert into VariableName(variableName, realName) values ('IO1TGFamilies', 'Target Group: Families/Parents');
insert into VariableName(variableName, realName) values ('IO1TGImpairments', 'Target Group: Clients with other impairments (physical, mental)');
insert into VariableName(variableName, realName) values ('IO1TGITProfession', 'Target Group: Clients with international training in a regulated profession');
insert into VariableName(variableName, realName) values ('IO1TGITTrade', 'Target Group: Clients with international training in a regulated trade');
insert into VariableName(variableName, realName) values ('IO1TGLanguageMin', 'Target Group: Official Language minorities');
insert into VariableName(variableName, realName) values ('IO1OverviewCanada', 'Overview of Canada');
insert into VariableName(variableName, realName) values ('IO1OverviewCanadaRef', 'Overview of Canada Referrals');
insert into VariableName(variableName, realName) values ('IO1SourcesInformation', 'Sources of Information');
insert into VariableName(variableName, realName) values ('IO1SourcesInformationRef', 'Sources of Information Referrals');
insert into VariableName(variableName, realName) values ('IO1RightsFreedoms', 'Rights and Freedoms');
insert into VariableName(variableName, realName) values ('IO1RightsFreedomsRef', 'Rights and Freedoms Referrals');
insert into VariableName(variableName, realName) values ('IO1CanadianLaw', 'Canadian Law and Justice');
insert into VariableName(variableName, realName) values ('IO1CanadianLawRef', 'Canadian Law and Justice Referrals');
insert into VariableName(variableName, realName) values ('IO1ImportantDocuments', 'Important Documents');
insert into VariableName(variableName, realName) values ('IO1ImportantDocumentsRef', 'Important Documents Referrals');
insert into VariableName(variableName, realName) values ('IO1ImprovingLanguage', 'Improving English or French');
insert into VariableName(variableName, realName) values ('IO1ImprovingLanguageRef', 'Improving English or French Referrals');
insert into VariableName(variableName, realName) values ('IO1EmploymentIncome', 'Employment and Income');
insert into VariableName(variableName, realName) values ('IO1EmploymentIncomeRef', 'Employment and Income Referrals');
insert into VariableName(variableName, realName) values ('IO1Education', 'Education');
insert into VariableName(variableName, realName) values ('IO1EducationRef', 'Education Referrals');
insert into VariableName(variableName, realName) values ('IO1Housing', 'Housing');
insert into VariableName(variableName, realName) values ('IO1HousingRef', 'Housing Referrals');
insert into VariableName(variableName, realName) values ('IO1Health', 'Health');
insert into VariableName(variableName, realName) values ('IO1HealthRef', 'Health Referrals');
insert into VariableName(variableName, realName) values ('IO1MoneyFinance', 'Money and Finances');
insert into VariableName(variableName, realName) values ('IO1MoneyFinanceRef', 'Money and Finances Referrals');
insert into VariableName(variableName, realName) values ('IO1Transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('IO1TransportationRef', 'Transportation Referrals');
insert into VariableName(variableName, realName) values ('IO1ComMedia', 'Communications and Media');
insert into VariableName(variableName, realName) values ('IO1ComMediaRef', 'Communications and Media Referrals');
insert into VariableName(variableName, realName) values ('IO1ComEngagement', 'Community Engagement');
insert into VariableName(variableName, realName) values ('IO1ComEngagementRef', 'Community Engagement Referrals');
insert into VariableName(variableName, realName) values ('IO1CanadianCitizen', 'Becoming a Canadian Citizen');
insert into VariableName(variableName, realName) values ('IO1CanadianCitizenRef', 'Becoming a Canadian Citizen Referrals');
insert into VariableName(variableName, realName) values ('IO1InterpersonalConflicts', 'Interpersonal Conflict');
insert into VariableName(variableName, realName) values ('IO1InterpersonalConflictsRef', 'Interpersonal Conflict Referrals');
insert into VariableName(variableName, realName) values ('IO1EssentialSkills', 'Was Essential Skills and Aptitude Training Received as Part of this Service?');
insert into VariableName(variableName, realName) values ('IO1ComputerSkills', 'Computer skills');
insert into VariableName(variableName, realName) values ('IO1DocumentUse', 'Document Use');
insert into VariableName(variableName, realName) values ('IO1InterpersonalSkills', 'Interpersonal Skills and Workplace Culture');
insert into VariableName(variableName, realName) values ('IO1LeadershipTraining', 'Leadership Training');
insert into VariableName(variableName, realName) values ('IO1Numeracy', 'Numeracy');
insert into VariableName(variableName, realName) values ('IO1LifeSkillsService', 'Was Life Skills or Responsibilities of Citizenship Information Received as Part of this Service?');
insert into VariableName(variableName, realName) values ('IO1LifeSkills', 'Life Skills');
insert into VariableName(variableName, realName) values ('IO1RightsCitizenship', 'Rights and Responsibilities of Citizenship (based on discover Canada)');
insert into VariableName(variableName, realName) values ('IO1SupportServices', 'Support Services Received');
insert into VariableName(variableName, realName) values ('IO1CareChildren', 'Care for Newcomer Children');
insert into VariableName(variableName, realName) values ('IO1C1Age', 'Child 1: Age');
insert into VariableName(variableName, realName) values ('IO1C1TypeCare', 'Child 1: Type of Care');
insert into VariableName(variableName, realName) values ('IO1C2Age', 'Child 2: Age');
insert into VariableName(variableName, realName) values ('IO1C2TypeCare', 'Child 2: Type of Care');
insert into VariableName(variableName, realName) values ('IO1C3Age', 'Child 3: Age');
insert into VariableName(variableName, realName) values ('IO1C3TypeCare', 'Child 3: Type of Care');
insert into VariableName(variableName, realName) values ('IO1C4Age', 'Child 4: Age');
insert into VariableName(variableName, realName) values ('IO1C4TypeCare', 'Child 4: Type of Care');
insert into VariableName(variableName, realName) values ('IO1C5Age', 'Child 5: Age');
insert into VariableName(variableName, realName) values ('IO1C5TypeCare', 'Child 5: Type of Care');
insert into VariableName(variableName, realName) values ('IO2Transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('IO1ProvisionsDisabilities', 'Provisions for Disabilities');
insert into VariableName(variableName, realName) values ('IO1Translation', 'Translation');
insert into VariableName(variableName, realName) values ('IO1TranslationBetween', 'Translation: Between');
insert into VariableName(variableName, realName) values ('IO1TranslationAnd', 'Translation: And');
insert into VariableName(variableName, realName) values ('IO1Interpretation', 'Interpretation');
insert into VariableName(variableName, realName) values ('IO1InterpretationBetween', 'Interpretation: Between');
insert into VariableName(variableName, realName) values ('IO1InterpretationAnd', 'Interpretation: And');
insert into VariableName(variableName, realName) values ('IO1CrisisCounselling', 'Crisis Counselling');
insert into VariableName(variableName, realName) values ('IO1EndDateService', 'End Date of Service (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('IO1UpdateReason', 'Reason for update');

# insert variable names in CC
Insert into VariableName(variableName, realName) values ('CC1UpdateRecordID', 'Update Record ID');
Insert into VariableName(variableName, realName) values ('CC1UniqueID', 'Unique Identifier');
Insert into VariableName(variableName, realName) values ('CC1UniqueIDVal', 'Unique Identifier Value');
Insert into VariableName(variableName, realName) values ('CC1DOB', 'Date of Birth (YYYY-MM-DD)');
Insert into VariableName(variableName, realName) values ('CC1PostalCode', 'Postal Code where the service was received');
Insert into VariableName(variableName, realName) values ('CC1ServiceLanguage', 'Language of Service');
Insert into VariableName(variableName, realName) values ('CC1OfficialLanguagePrefered', 'Official Language of Preference');
Insert into VariableName(variableName, realName) values ('CC1ReferredBy', 'Referred By');
Insert into VariableName(variableName, realName) values ('CC1ClientServiceActivity', 'Activity Under Which Client Received Services');
Insert into VariableName(variableName, realName) values ('CC1InstitutionType', 'Type of Institution/Organization Where Client Received Services');
Insert into VariableName(variableName, realName) values ('CC1TypeofEventAttended', 'Type of Event Attended');
Insert into VariableName(variableName, realName) values ('CC1TypeofService', 'Type of Service');
Insert into VariableName(variableName, realName) values ('CC1MainTopicOfServices', 'Main Topic/Focus of the Service Received');
Insert into VariableName(variableName, realName) values ('CC1ServiceReceived', 'Service Received');
Insert into VariableName(variableName, realName) values ('CC1NumOfUniqueParticipants', 'Number of Unique Participants');
Insert into VariableName(variableName, realName) values ('CC1VolunteerParticipated', 'Did Volunteers from the Host Community Participate in the Activity');
Insert into VariableName(variableName, realName) values ('CC1SpecificTargetGroup', 'Directed at a Specific Target Group');
Insert into VariableName(variableName, realName) values ('CC1TG_Children', 'Target Group: Children (0-14 yrs)');
Insert into VariableName(variableName, realName) values ('CC1TG_Youth', 'Target Group: Youth (15-24 yrs)');
Insert into VariableName(variableName, realName) values ('CC1TG_Senior', 'Target Group: Senior');
Insert into VariableName(variableName, realName) values ('CC1TG_Gender', 'Target Group: Gender-specific');
Insert into VariableName(variableName, realName) values ('CC1TG_Refugees', 'Target Group: Refugees');
Insert into VariableName(variableName, realName) values ('CC1TG_Ethnic', 'Target Group: Ethnic/cultural/linguistic group');
Insert into VariableName(variableName, realName) values ('CC1TG_DeaforHardofHearing', 'Target Group: Deaf or Hard of Hearing');
Insert into VariableName(variableName, realName) values ('CC1TG_BlindorPartiallySighted', 'Target Group: Blind or Partially Sighted');
Insert into VariableName(variableName, realName) values ('CC1TG_LGBTQ', 'Target Group: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)');
Insert into VariableName(variableName, realName) values ('CC1TG_Families', 'Target Group: Families/Parents');
Insert into VariableName(variableName, realName) values ('CC1TG_Otherimpairments', 'Target Group: Other impairments (physical, mental)');
Insert into VariableName(variableName, realName) values ('CC1TG_ClientsWithInternationalTraining_RegulatedProfession', 'Target Group: Clients with international training in a regulated profession');
Insert into VariableName(variableName, realName) values ('CC1TG_ClientswithInternationalTraining_RegulatedTrade', 'Target Group: Clients with international training in a regulated trade');
Insert into VariableName(variableName, realName) values ('CC1TG_OfficialLanguageMinorities', 'Target Group: Official language minorities');
Insert into VariableName(variableName, realName) values ('CC1StatusofService', 'Status of Service');
Insert into VariableName(variableName, realName) values ('CC1ReasonforLeavingService', 'Reason for Leaving Service');
Insert into VariableName(variableName, realName) values ('CC1StartDate', 'Start Date (YYYY-MM-DD)');
Insert into VariableName(variableName, realName) values ('CC1EndDate', 'End Date (YYYY-MM-DD)');
Insert into VariableName(variableName, realName) values ('CC1ProjectedEndDate', 'Projected End Date (YYYY-MM-DD)');
Insert into VariableName(variableName, realName) values ('CC1EssentialSkills', 'Was Essential Skills and Aptitudes Training Received as Part of the Service?');
Insert into VariableName(variableName, realName) values ('CC1ComputerSkills', 'Computer Skills');
Insert into VariableName(variableName, realName) values ('CC1DocumentUse', 'Document Use');
Insert into VariableName(variableName, realName) values ('CC1InterpersonalSkills', 'Interpersonal Skills and Workplace Culture');
Insert into VariableName(variableName, realName) values ('CC1LeadershipTraining', 'Leadership Training');
Insert into VariableName(variableName, realName) values ('CC1LifeSkills', 'Life Skills');
Insert into VariableName(variableName, realName) values ('CC1Numeracy', 'Numeracy');
Insert into VariableName(variableName, realName) values ('CC1SupportServicesReceived', 'Support Services Received');
Insert into VariableName(variableName, realName) values ('CC1CareforChildren', 'Care for Newcomer Children');
Insert into VariableName(variableName, realName) values ('CC1Child1Age', 'Child 1: Age');
Insert into VariableName(variableName, realName) values ('CC1Child1TypeofCare', 'Child 1: Type of Care');
Insert into VariableName(variableName, realName) values ('CC1Child2Age', 'Child 2: Age');
Insert into VariableName(variableName, realName) values ('CC1Child2TypeofCare', 'Child 2: Type of Care');
Insert into VariableName(variableName, realName) values ('CC1Child3Age', 'Child 3: Age');
Insert into VariableName(variableName, realName) values ('CC1Child4TypeofCare', 'Child 4: Type of Care');
Insert into VariableName(variableName, realName) values ('CC1Child5Age', 'Child 5: Age');
Insert into VariableName(variableName, realName) values ('CC1Child5TypeofCare', 'Child 5: Type of Care');
Insert into VariableName(variableName, realName) values ('CC1Transportation', 'Transportation');
Insert into VariableName(variableName, realName) values ('CC1ProvisionsforDisabilities', 'Provisions for Disabilities');
Insert into VariableName(variableName, realName) values ('CC1Translation', 'Translation');
Insert into VariableName(variableName, realName) values ('CC1TranslationBetween', 'Between');
Insert into VariableName(variableName, realName) values ('CC1TranslationAnd', 'And');
Insert into VariableName(variableName, realName) values ('CC1Interpretation', 'Interpretation');
Insert into VariableName(variableName, realName) values ('CC1InterpretationBetween', 'Interpretation: Between');
Insert into VariableName(variableName, realName) values ('CC1InterpretationAnd', 'Interpretation: And');
Insert into VariableName(variableName, realName) values ('CC1CrisisCounselling', 'Crisis Counselling');
Insert into VariableName(variableName, realName) values ('CC1LengthofServiceHours', 'Total Length of Service: Hours');
Insert into VariableName(variableName, realName) values ('CC1LengthofServiceMinutes', 'Total Length of Service: Minutes');
Insert into VariableName(variableName, realName) values ('CC1Reasonforupdate', 'Reason for update');

#Insert column names for LTClientEnrolment - LTCE
Insert into VariableName(variableName, realName) values ('LTCE1UpdaterecordID', 'Update record ID');
Insert into VariableName(variableName, realName) values ('LTCE1UniqueIDType', 'Unique Identifier Type');
Insert into VariableName(variableName, realName) values ('LTCE1UniqueIDValue', 'Unique Identifier Value');
Insert into VariableName(variableName, realName) values ('LTCE1DOB', 'Client Date of Birth (YYYY-MM-DD)');
Insert into VariableName(variableName, realName) values ('LTCE1PostalCode', 'Postal Code where the service was received');
Insert into VariableName(variableName, realName) values ('LTCE1CourseCode', 'Course Code');
Insert into VariableName(variableName, realName) values ('LTCE1DateOfFistClass', 'Date of Client\'s First Class (YYYY-MM-DD)');
Insert into VariableName(variableName, realName) values ('LTCE1OfficialLanguagePrefered', 'Official Language of Preference');
Insert into VariableName(variableName, realName) values ('LTCE1SupportServicesReceived', 'Support Services Received');
Insert into VariableName(variableName, realName) values ('LTCE1CareforChildren', 'Care for Newcomer Children');
Insert into VariableName(variableName, realName) values ('LTCE1Child1Age', 'Child 1: Age');
Insert into VariableName(variableName, realName) values ('LTCE1Child1TypeofCare', 'Child 1: Type of Care');
Insert into VariableName(variableName, realName) values ('LTCE1Child2Age', 'Child 2: Age');
Insert into VariableName(variableName, realName) values ('LTCE1Child2TypeofCare', 'Child 2: Type of Care');
Insert into VariableName(variableName, realName) values ('LTCE1Child3Age', 'Child 3: Age');
Insert into VariableName(variableName, realName) values ('LTCE1Child4TypeofCare', 'Child 4: Type of Care');
Insert into VariableName(variableName, realName) values ('LTCE1Child5Age', 'Child 5: Age');
Insert into VariableName(variableName, realName) values ('LTCE1Child5TypeofCare', 'Child 5: Type of Care');
Insert into VariableName(variableName, realName) values ('LTCE1Transportation', 'Transportation');
Insert into VariableName(variableName, realName) values ('LTCE1ProvisionsforDisabilities', 'Provisions for Disabilities');
Insert into VariableName(variableName, realName) values ('LTCE1Translation', 'Translation');
Insert into VariableName(variableName, realName) values ('LTCE1TranslationBetween', 'Between');
Insert into VariableName(variableName, realName) values ('LTCE1TranslationAnd', 'And');
Insert into VariableName(variableName, realName) values ('LTCE1Interpretation', 'Interpretation');
Insert into VariableName(variableName, realName) values ('LTCE1InterpretationBetween', 'Between');
Insert into VariableName(variableName, realName) values ('LTCE1InterpretationAnd', 'And');
Insert into VariableName(variableName, realName) values ('LTCE1CrisisCounselling', 'Crisis Counselling');
Insert into VariableName(variableName, realName) values ('LTCE1Reasonforupdate', 'Reason for update');

#Insert column names of ClientProfile - CP
insert into VariableName(variableName, realName) values ('CP1UniqueID', 'Unique Identifier');
insert into VariableName(variableName, realName) values ('CP1UniqueIDValue', 'Unique Identifier Value');
insert into VariableName(variableName, realName) values ('CP1DOB', 'Date of Birth (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('CP1PhoneNum', 'Phone Number');
insert into VariableName(variableName, realName) values ('CP1QuesEmailAddress', 'Does the Client Have an Email Address');
insert into VariableName(variableName, realName) values ('CP1EmailAddress', 'Email Address');
insert into VariableName(variableName, realName) values ('CP1StreetNum', 'Street Number');
insert into VariableName(variableName, realName) values ('CP1StreetName', 'Street Name');
insert into VariableName(variableName, realName) values ('CP1StreetType', 'Street Type');
insert into VariableName(variableName, realName) values ('CP1StreetDirection', 'Street Direction');
insert into VariableName(variableName, realName) values ('CP1UnitNum', 'Unit/Suite/Apt');
insert into VariableName(variableName, realName) values ('CP1City', 'City');
insert into VariableName(variableName, realName) values ('CP1Province', 'Province');
insert into VariableName(variableName, realName) values ('CP1PostalCode', 'Postal Code');
insert into VariableName(variableName, realName) values ('CP1OfficialLanguagePrefered', 'Official Language of Preference');
insert into VariableName(variableName, realName) values ('CP1ConsetForResearch', 'Consent for Future Research/Consultation');

#Insert column names of Needs Accessement and Referrals -NAR
insert into VariableName(variableName, realName) values ('NAR1UpdateRecordID', 'Update Record ID');
insert into VariableName(variableName, realName) values ('NAR1UniqueID', 'Unique Identifier');
insert into VariableName(variableName, realName) values ('NAR1UniqueIDValue', 'Unique Identifier Value');
insert into VariableName(variableName, realName) values ('NAR1DOB', 'Date of Birth (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('NAR1PostalCode', 'Postal Code where the service was received');
insert into VariableName(variableName, realName) values ('NAR1StartDate', 'Start Date of Assessment (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('NAR1ServiceLanguage', 'Language of Service');
insert into VariableName(variableName, realName) values ('NAR1OfficialLanguagePrefered', 'Official Language of Preference');
insert into VariableName(variableName, realName) values ('NAR1InstitutionType', 'Type of Institution/Organization Where Client Received Services');
insert into VariableName(variableName, realName) values ('NAR1ReferredBy', 'Referred By');
insert into VariableName(variableName, realName) values ('NAR1IncLifeInCanada', 'Increase knowledge of: Life in Canada');
insert into VariableName(variableName, realName) values ('NAR1IncLifeInCanadaRef', 'Increase knowledge of: Life in Canada Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncCommunityService', 'Increase knowledge of: Community and Government Services');
insert into VariableName(variableName, realName) values ('NAR1IncCommunityServiceRef', 'Increase knowledge of: Community and Government Services Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncWorkInCanada', 'Increase knowledge of: Working in Canada');
insert into VariableName(variableName, realName) values ('NAR1IncWorkInCanadaRef', 'Increase knowledge of: Working in Canada Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncEduInCanada', 'Increase knowledge of: Education in Canada');
insert into VariableName(variableName, realName) values ('NAR1IncEduInCanadaRef', 'Increase knowledge of: Education in Canada Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncSocialNetworks', 'Increase the following: Social networks');
insert into VariableName(variableName, realName) values ('NAR1IncSocialNetworksRef', 'Increase the following: Social networks Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncProfNetworks', 'Increase the following: Professional networks');
insert into VariableName(variableName, realName) values ('NAR1IncProfNetworksRef', 'Increase the following: Professional networks Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncLocCommunityService', 'Increase the following: Access to local community services');
insert into VariableName(variableName, realName) values ('NAR1IncLocCommunityServiceRef', 'Increase the following: Access to local community services Referrals');
insert into VariableName(variableName, realName) values ('NAR1IncCommunityInvolvement', 'Increase the following: Level of community involvement');
insert into VariableName(variableName, realName) values ('NAR1IncCommunityInvolvementRef', 'Increase the following: Level of community involvement Referrals');
insert into VariableName(variableName, realName) values ('NAR1ImpLanguageSkill', 'Improve Language Skills');
insert into VariableName(variableName, realName) values ('NAR1ImpLanguageSkillRef', 'Improve Language Skills Referrals');
insert into VariableName(variableName, realName) values ('NAR1ReasonLanguageSkill', 'Improve Language Skills to');
insert into VariableName(variableName, realName) values ('NAR1ImpOtherSkill', 'Improve Other Skills');
insert into VariableName(variableName, realName) values ('NAR1ImpOtherSkillRef', 'Improve Other Skills Referrals');
insert into VariableName(variableName, realName) values ('NAR1ReasonOtherSkill', 'Improve Other Skills to');
insert into VariableName(variableName, realName) values ('NAR1FindEmployment', 'Find employment');
insert into VariableName(variableName, realName) values ('NAR1FindEmploymentRef', 'Find employment Referrals');
insert into VariableName(variableName, realName) values ('NAR1TimeFrame', 'Find employment: TimeFrame');
insert into VariableName(variableName, realName) values ('NAR1WorkExp', 'Find employment: Minimum one year\'s work experience?');
insert into VariableName(variableName, realName) values ('NAR1IntendWorkOccupation', 'Find employment: Intends to work in an occupation corresponding to which National Occupation Classification skill level?');
insert into VariableName(variableName, realName) values ('NAR1IntendObtainCredential', 'Find employment: Intends to obtain credential recognition or obtain license to work in Canada?');
insert into VariableName(variableName, realName) values ('NAR1IntendCanadianCitizen', 'Client intends to become a Canadian citizen?');
insert into VariableName(variableName, realName) values ('NAR1SupportServiceRequired', 'Support services may be required');
insert into VariableName(variableName, realName) values ('NAR1CareChildren', 'Care for Newcomer Children');
insert into VariableName(variableName, realName) values ('NAR1Transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('NAR1Disability', 'Provisions for Disabilities');
insert into VariableName(variableName, realName) values ('NAR1Translation', 'Translation');
insert into VariableName(variableName, realName) values ('NAR1Interpretation', 'Interpretation');
insert into VariableName(variableName, realName) values ('NAR1CrisisCounselling', 'Crisis Counselling');
insert into VariableName(variableName, realName) values ('NAR1NonIRCCServicesNeeded', 'Non-IRCC program services needed');
insert into VariableName(variableName, realName) values ('NAR1MaterialNeeds', 'Food/Clothing/Other Material Needs');
insert into VariableName(variableName, realName) values ('NAR1MaterialNeedsRef', 'Food/Clothing/Other Material Needs Referrals');
insert into VariableName(variableName, realName) values ('NAR1Accommodation', 'Housing/Accommodation');
insert into VariableName(variableName, realName) values ('NAR1AccommodationRef', 'Housing/Accommodation Referrals');
insert into VariableName(variableName, realName) values ('NAR1Health', 'Health/Mental Health/Well Being');
insert into VariableName(variableName, realName) values ('NAR1HealthRef', 'Health/Mental Health/Well Being Referrals');
insert into VariableName(variableName, realName) values ('NAR1Financial', 'Financial');
insert into VariableName(variableName, realName) values ('NAR1FinancialRef', 'Financial Referrals');
insert into VariableName(variableName, realName) values ('NAR1FamilySupport', 'Family Support');
insert into VariableName(variableName, realName) values ('NAR1FamilySupportRef', 'Family Support Referrals');
insert into VariableName(variableName, realName) values ('NAR1LanguageNonIRCC', 'Language (Non-IRCC)');
insert into VariableName(variableName, realName) values ('NAR1LanguageNonIRCCRef', 'Language (Non-IRCC) Referrals');
insert into VariableName(variableName, realName) values ('NAR1Education', 'Education/Skills Development');
insert into VariableName(variableName, realName) values ('NAR1EducationRef', 'Education/Skills Development Referrals');
insert into VariableName(variableName, realName) values ('NAR1EmploymentRelated', 'Employment-related');
insert into VariableName(variableName, realName) values ('NAR1EmploymentRelatedRef', 'Employment-related Referrals');
insert into VariableName(variableName, realName) values ('NAR1LegalInfoAndService', 'Legal Information and Services');
insert into VariableName(variableName, realName) values ('NAR1LegalInfoAndServiceRef', 'Legal Information and Services Referrals');
insert into VariableName(variableName, realName) values ('NAR1CommunityService', 'Community Services');
insert into VariableName(variableName, realName) values ('NAR1CommunityServiceRef', 'Community Services Referrals');
insert into VariableName(variableName, realName) values ('NAR1SupportServiceReceived', 'Support Services Received');
insert into VariableName(variableName, realName) values ('NAR2CareChildren', 'Care for Newcomer Children');
insert into VariableName(variableName, realName) values ('NAR1Child1Age', 'Child 1: Age');
insert into VariableName(variableName, realName) values ('NAR1Child1Type', 'Child 1: Type of Care');
insert into VariableName(variableName, realName) values ('NAR1Child2Age', 'Child 2: Age');
insert into VariableName(variableName, realName) values ('NAR1Child2Type', 'Child 2: Type of Care');
insert into VariableName(variableName, realName) values ('NAR1Child3Age', 'Child 3: Age');
insert into VariableName(variableName, realName) values ('NAR1Child3Type', 'Child 3: Type of Care');
insert into VariableName(variableName, realName) values ('NAR1Child4Age', 'Child 4: Age');
insert into VariableName(variableName, realName) values ('NAR1Child4Type', 'Child 4: Type of Care');
insert into VariableName(variableName, realName) values ('NAR1Child5Age', 'Child 5: Age');
insert into VariableName(variableName, realName) values ('NAR1Child5Type', 'Child 5: Type of Care');
insert into VariableName(variableName, realName) values ('NAR2Transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('NAR2Disability', 'Provisions for Disabilities');
insert into VariableName(variableName, realName) values ('NAR2Translation', 'Translation');
insert into VariableName(variableName, realName) values ('NAR1TranslationBetween', 'Between');
insert into VariableName(variableName, realName) values ('NAR1TranslationAnd', 'And');
insert into VariableName(variableName, realName) values ('NAR2Interpretation', 'Interpretation');
insert into VariableName(variableName, realName) values ('NAR1InterpretationBetween', 'Between');
insert into VariableName(variableName, realName) values ('NAR1InterpretationAnd', 'And');
insert into VariableName(variableName, realName) values ('NAR2CrisisCounselling', 'Crisis Counselling');
insert into VariableName(variableName, realName) values ('NAR1SettlementPlanCompleted', 'Settlement Plan completed and shared with client');
insert into VariableName(variableName, realName) values ('NAR1EndDate', 'End Date of Assessment (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('NAR1ReasonUpdate', 'Reason for update');

# insert variable names for LTS
insert into VariableName(variableName, realName) values ('LTS1updateRecordID', 'Update record ID');
insert into VariableName(variableName, realName) values ('LTS1courseCode', 'Course Code');
insert into VariableName(variableName, realName) values ('LTS1notes', 'Notes');
insert into VariableName(variableName, realName) values ('LTS1courseHeldOnAnOngoingBasis', 'Course Held On An Ongoing Basis');
insert into VariableName(variableName, realName) values ('LTS1officialLanguageOfCourse', 'Official Language of Course');
insert into VariableName(variableName, realName) values ('LTS1formatOfTrainingProvided', 'Format of Training Provided');
insert into VariableName(variableName, realName) values ('LTS1classesHeldAt', 'Classes Held At');
insert into VariableName(variableName, realName) values ('LTS1inPersonInstruction(%)', 'In-Person Instruction (%)');
insert into VariableName(variableName, realName) values ('LTS1onlineDistanceInstruction(%)', 'Online/Distance Instruction (%)');
insert into VariableName(variableName, realName) values ('LTS1totalNumberofSpotsinCourse', 'Total Number of Spots in Course');
insert into VariableName(variableName, realName) values ('LTS1numberOfIRCCFundedSpotsinCourse', 'Number of IRCC-Funded Spots in Course');
insert into VariableName(variableName, realName) values ('LTS1newStudentsCanEnrolInTheCourse', 'New Students Can Enrol in the Course');
insert into VariableName(variableName, realName) values ('LTS1supportServicesAvailableForClientInThisCourse', 'Support Services Available for Client in this Course');
insert into VariableName(variableName, realName) values ('LTS1careForNewcomerChildren', 'Care for Newcomer Children');
insert into VariableName(variableName, realName) values ('LTS1transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('LTS1provisionsForDisabilities', 'Provisions for Disabilities');
insert into VariableName(variableName, realName) values ('LTS1courseStartDate', 'Course Start Date (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('LTS1courseEndDate', 'Course End Date (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('LTS1scheduleMorning', 'Schedule: Morning');
insert into VariableName(variableName, realName) values ('LTS1scheduleAfternoon', 'Schedule: Afternoon');
insert into VariableName(variableName, realName) values ('LTS1scheduleEvening', 'Schedule: Evening');
insert into VariableName(variableName, realName) values ('LTS1scheduleWeekend', 'Schedule: Weekend');
insert into VariableName(variableName, realName) values ('LTS1scheduleAnytime', 'Schedule: Anytime');
insert into VariableName(variableName, realName) values ('LTS1scheduleOnline', 'Schedule: Online');
insert into VariableName(variableName, realName) values ('LTS1instructionalHoursPerClass', 'Instructional Hours Per Class');
insert into VariableName(variableName, realName) values ('LTS1classesPerWeek', 'Classes Per Week');
insert into VariableName(variableName, realName) values ('LTS1weeksOfInstruction', 'Weeks of Instruction');
insert into VariableName(variableName, realName) values ('LTS1weeksOfInstructionPerYear', 'Weeks of Instruction Per Year');
insert into VariableName(variableName, realName) values ('LTS1dominantFocusOfTheCourse', 'Dominant Focus of the Course');
insert into VariableName(variableName, realName) values ('LTS1courseDirectedAtASpecificTargetGroup', 'Course Directed at a Specific Target Group');
insert into VariableName(variableName, realName) values ('LTS1children (0-14 yrs)', 'Children (0-14 yrs)');
insert into VariableName(variableName, realName) values ('LTS1youth (15-24 yrs)', 'Youth (15-24 yrs)');
insert into VariableName(variableName, realName) values ('LTS1senior', 'Senior');
insert into VariableName(variableName, realName) values ('LTS1genderSpecific', 'Gender-specific');
insert into VariableName(variableName, realName) values ('LTS1refugees', 'Refugees');
insert into VariableName(variableName, realName) values ('LTS1officialLanguageMinorities', 'Official language minorities');
insert into VariableName(variableName, realName) values ('LTS1ethnicCulturalLinguisticGroup', 'Ethnic/cultural/linguistic group');
insert into VariableName(variableName, realName) values ('LTS1deafOrHardOfHearing', 'Deaf or Hard of Hearing');
insert into VariableName(variableName, realName) values ('LTS1blindOrPartiallySighted', 'Blind or Partially Sighted');
insert into VariableName(variableName, realName) values ('LTS1clientsWithOtherImpairments (physical, mental)', 'Clients with other impairments (physical, mental)');
insert into VariableName(variableName, realName) values ('LTS1lesbianGayBisexualTransgenderQueer (LGBTQ)', 'Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)');
insert into VariableName(variableName, realName) values ('LTS1familiesParents', 'Families/Parents');
insert into VariableName(variableName, realName) values ('LTS1clientsWithInternationalTrainingInARegulatedProfession', 'Clients with international training in a regulated profession');
insert into VariableName(variableName, realName) values ('LTS1clientsWithInternationalTrainingInARegulatedTrade', 'Clients with international training in a regulated trade');
insert into VariableName(variableName, realName) values ('LTS1materialsUsedInCourse', 'Materials Used in Course');
insert into VariableName(variableName, realName) values ('LTS1citizenshipPreparation', 'Citizenship preparation');
insert into VariableName(variableName, realName) values ('LTS1PBLALanguageCompanion', 'PBLA language companion');
insert into VariableName(variableName, realName) values ('LTS1contactName', 'Contact Name');
insert into VariableName(variableName, realName) values ('LTS1streetNumber', 'Street Number');
insert into VariableName(variableName, realName) values ('LTS1streetName', 'Street Name');
insert into VariableName(variableName, realName) values ('LTS1streetType', 'Street Type');
insert into VariableName(variableName, realName) values ('LTS1streetDirection', 'Street Direction');
insert into VariableName(variableName, realName) values ('LTS1unitSuite', 'Unit/Suite');
insert into VariableName(variableName, realName) values ('LTS1province', 'Province');
insert into VariableName(variableName, realName) values ('LTS1city', 'City');
insert into VariableName(variableName, realName) values ('LTS1postalCode', 'Postal Code (A#A#A#)');
insert into VariableName(variableName, realName) values ('LTS1telephoneNumber', 'Telephone Number (###-###-####)');
insert into VariableName(variableName, realName) values ('LTS1telephoneExtension', 'Telephone Extension');
insert into VariableName(variableName, realName) values ('LTS1emailAddress', 'Email Address');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel1', 'Listening Skill Level 1');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel2', 'Listening Skill Level 2');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel3', 'Listening Skill Level 3');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel4', 'Listening Skill Level 4');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel5', 'Listening Skill Level 5');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel6', 'Listening Skill Level 6');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel7', 'Listening Skill Level 7');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel8', 'Listening Skill Level 8');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel9', 'Listening Skill Level 9');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel10', 'Listening Skill Level 10');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel11', 'Listening Skill Level 11');
insert into VariableName(variableName, realName) values ('LTS1listeningSkillLevel12', 'Listening Skill Level 12');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel1', 'Speaking Skill Level 1');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel2', 'Speaking Skill Level 2');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel3', 'Speaking Skill Level 3');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel4', 'Speaking Skill Level 4');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel5', 'Speaking Skill Level 5');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel6', 'Speaking Skill Level 6');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel7', 'Speaking Skill Level 7');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel8', 'Speaking Skill Level 8');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel9', 'Speaking Skill Level 9');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel10', 'Speaking Skill Level 10');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel11', 'Speaking Skill Level 11');
insert into VariableName(variableName, realName) values ('LTS1speakingSkillLevel12', 'Speaking Skill Level 12');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel1', 'Reading Skill Level 1');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel2', 'Reading Skill Level 2');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel3', 'Reading Skill Level 3');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel4', 'Reading Skill Level 4');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel5', 'Reading Skill Level 5');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel6', 'Reading Skill Level 6');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel7', 'Reading Skill Level 7');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel8', 'Reading Skill Level 8');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel9', 'Reading Skill Level 9');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel10', 'Reading Skill Level 10');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel11', 'Reading Skill Level 11');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel12', 'Reading Skill Level 12');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel13', 'Reading Skill Level 13');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel14', 'Reading Skill Level 14');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel15', 'Reading Skill Level 15');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel16', 'Reading Skill Level 16');
insert into VariableName(variableName, realName) values ('LTS1readingSkillLevel17', 'Reading Skill Level 17');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel1', 'Writing Skill Level 1');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel2', 'Writing Skill Level 2');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel3', 'Writing Skill Level 3');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel4', 'Writing Skill Level 4');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel5', 'Writing Skill Level 5');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel6', 'Writing Skill Level 6');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel7', 'Writing Skill Level 7');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel8', 'Writing Skill Level 8');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel9', 'Writing Skill Level 9');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel10', 'Writing Skill Level 10');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel11', 'Writing Skill Level 11');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel12', 'Writing Skill Level 12');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel13', 'Writing Skill Level 13');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel14', 'Writing Skill Level 14');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel15', 'Writing Skill Level 15');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel16', 'Writing Skill Level 16');
insert into VariableName(variableName, realName) values ('LTS1writingSkillLevel17', 'Writing Skill Level 17');

# insert variable names for LTExit
insert into VariableName(variableName, realName) values ('LTE1updateRecordID', 'Update record ID');
insert into VariableName(variableName, realName) values ('LTE1uniqueIdentifierType', 'Unique Identifier Type');
insert into VariableName(variableName, realName) values ('LTE1uniqueIdentifierValue', 'Unique Identifier Value');
insert into VariableName(variableName, realName) values ('LTE1clientDateOfBirth', 'Client Date of Birth (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('LTE1courseCode', 'Course Code');
insert into VariableName(variableName, realName) values ('LTE1clientTrainingStatus', 'Client\'s Training Status');
insert into VariableName(variableName, realName) values ('LTE1dateClientExitedCourse', 'Date Client Exited Course (YYYY-MM-DD)');
insert into VariableName(variableName, realName) values ('LTE1reasonForExitingCourse', 'Reason for Exiting course');
insert into VariableName(variableName, realName) values ('LTE1listeningCLBLevel', 'Listening CLB Level');
insert into VariableName(variableName, realName) values ('LTE1speakingCLBLevel', 'Speaking CLB Level');
insert into VariableName(variableName, realName) values ('LTE1readingCLBLevel', 'Reading CLB Level');
insert into VariableName(variableName, realName) values ('LTE1writingCLBLevel', 'Writing CLB Level');
insert into VariableName(variableName, realName) values ('LTE1wasACertificateIssuedToTheClient', 'Was a Certificate issued to the client?');
insert into VariableName(variableName, realName) values ('LTE1listeningLevelIndicatedOnCertificate', 'Listening level indicated on Certificate');
insert into VariableName(variableName, realName) values ('LTE1speakingLevelIndicatedOnCertificate', 'Speaking level indicated on Certificate');
insert into VariableName(variableName, realName) values ('LTE1supportServicesReceived', 'Support services received');
insert into VariableName(variableName, realName) values ('LTE1careForNewcomerChildren', 'Care for newcomer children');
insert into VariableName(variableName, realName) values ('LTE1child1Age', 'Child 1: Age');
insert into VariableName(variableName, realName) values ('LTE1child1TypeOfCare', 'Child 1: Type of Care');
insert into VariableName(variableName, realName) values ('LTE1child2Age', 'Child 2: Age');
insert into VariableName(variableName, realName) values ('LTE1child2TypeOfCare', 'Child 2: Type of Care');
insert into VariableName(variableName, realName) values ('LTE1child3Age', 'Child 3: Age');
insert into VariableName(variableName, realName) values ('LTE1child3TypeOfCare', 'Child 3: Type of Care');
insert into VariableName(variableName, realName) values ('LTE1child4Age', 'Child 4: Age');
insert into VariableName(variableName, realName) values ('LTE1child4TypeOfCare', 'Child 4: Type of Care');
insert into VariableName(variableName, realName) values ('LTE1child5Age', 'Child 5: Age');
insert into VariableName(variableName, realName) values ('LTE1child5TypeOfCare', 'Child 5: Type of Care');
insert into VariableName(variableName, realName) values ('LTE1transportation', 'Transportation');
insert into VariableName(variableName, realName) values ('LTE1provisionsForDisabilities', 'Provisions for disabilities');
insert into VariableName(variableName, realName) values ('LTE1translation', 'Translation');
insert into VariableName(variableName, realName) values ('LTE1translationLanguageBetween', 'Translation language Between');
insert into VariableName(variableName, realName) values ('LTE1translationLanguageAnd', 'Translation language And');
insert into VariableName(variableName, realName) values ('LTE1interpretation', 'Interpretation');
insert into VariableName(variableName, realName) values ('LTE1interpretationBetween', 'Between');
insert into VariableName(variableName, realName) values ('LTE1interpretationAnd', 'And');
insert into VariableName(variableName, realName) values ('LTE1crisisCounselling', 'Crisis Counselling');
insert into VariableName(variableName, realName) values ('LTE1reasonForUpdate', 'Reason for update');

