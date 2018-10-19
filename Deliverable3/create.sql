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
	`templateId` int primary key,
    constraint Efk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`Update Record ID` char(8),
	`Unique Identifier` int not null,
	constraint Efk2 foreign key (`Unique Identifier`) references UniqueIdentifier(identifierId),
	`Unique Identifier Value` char(8) not null,
	`Date of Birth (YYYY-MM-DD)` date not null,
	`Postal Code where the service was received` char(6) not null,
    `Registration in an employment intervation` bool not null,
    `A referral to` int not null,
	constraint Efk3 foreign key (`A referral to`) references AReferralTo(aReferralToId),
	`Language of Service` int not null,
    constraint Efk4 foreign key (`Language of Service`) references ServiceLanguage(serviceLanguageId),
	`Official Language of Preference` int not null,
    constraint Efk5 foreign key (`Official Language of Preference`) references LanguagePreference(preferLanguageId),
	`Type of Institution/Organization Where Client Received Services` int not null,
	constraint Efk6 foreign key (`Type of Institution/Organization Where Client Received Services`) references InstitutionType(institutionTypeId),
	`Referred By`int not null,
    constraint Efk7 foreign key (`Referred By`) references Referrer(reffererId),
    `Referral Date (YYYY-MM-DD)` date,
    `Employment Status`int not null,
    constraint Efk8 foreign key (`Employment Status`) references EmploymentStatus(employmentStatusId),
	`Education Status`int,
    constraint Efk9 foreign key (`Education Status`) references EducationStatus(educationStatusId),
    `Occupation in Canada`int,
    constraint Efk10 foreign key (`Occupation in Canada`) references Occupations(occupationsId),
    `Intended Occupation`int,
    constraint Efk11 foreign key (`Intended Occupation`) references Occupations(occupationsId),
    `Intervention Type`int,
    constraint Efk12 foreign key (`Intervention Type`) references InterventionType(interventionTypeId),
    `Long Term Intervention: Intervation Received`int,
    constraint Efk13 foreign key (`Long Term Intervention: Intervation Received`) references LongInterventionReceived(longTermIntervationReceivedId),
	`Long Term Intervention: Status of Intervation`int,
    constraint Efk14 foreign key (`Long Term Intervention: Status of Intervation`) references LongStatusOfIntervention(statusOfInterventionId),
	`Long Term Intervention: Reason For Leaving Intervention`int,
    constraint Efk15 foreign key (`Long Term Intervention: Reason For Leaving Intervention`) references LongLeavingIntervention(leavingInterventionId),
    `Long Term Intervention: End Date (YYYY-MM-DD)` date,
	`Long Term Intervention: Size of Employer`int,
    constraint Efk16 foreign key (`Long Term Intervention: Size of Employer`) references LongSizeEmployerIntervention(sizeEmployerInterventionId),
	`Long Term Intervention: Placement Was`int,
    constraint Efk17 foreign key (`Long Term Intervention: Placement Was`) references LongPlacementIntervention(placementInterventionId),
	`Long Term Intervention: Hours Per Week`int,
    constraint Efk18 foreign key (`Long Term Intervention: Hours Per Week`) references LongHoursPerWeekIntervention(hoursPerWeekInterventionId),
	`Long Term Intervention: Client Met Mentor Regularly at`int,
    constraint Efk19 foreign key (`Long Term Intervention: Client Met Mentor Regularly at`) references LongMetMentorIntervention(metMentorInterventionId),
	`Long Term Intervention: Average Hours/Week in Contact with Mentor`int,
    constraint Efk20 foreign key (`Average Hours/Week in Contact with Mentor`) references LongHoursMetMentorIntervention(hoursMetMentorInterventionId),
	`Long Term Intervention: Profession/Trade For Which Services Were Received`int,
    constraint Efk21 foreign key (`Long Term Intervention: Profession/Trade For Which Services Were Received`) references LongServicesReceivedIntervention(serviceReceivedInterventionId),
    `Was Essential Skills and Aptitude Training Received as Part of this Service?` bool,
    `Computer skills` bool,
    `Document Use` bool,
	`Interpersonal Skills and Workplace Culture` bool,
    `Leadership Training` bool,
    `Numeracy` bool,
	`Short Term Intervention: Service Received`int,
    constraint Efk22 foreign key (`Short Term Intervention: Service Received`) references ShortInterventionServiceReceived(shortInterventionServiceReceivedId),
	`Short Term Intervention: Date (YYYY-MM-DD)` date,
	`Support Services Received` bool,
	`Care for Newcomer Children` bool,
	`Child 1: Age` int,
    constraint Efk23 foreign key (`Child 1: Age`) references Age(ageId),
	`Child 1: Type of Care` int,
    constraint Efk24 foreign key (`Child 1: Type of Care`) references CareType(careTypeId),
	`Child 2: Age` int,
    constraint Efk25 foreign key (`Child 2: Age`) references Age(ageId),
	`Child 2: Type of Care` int,
    constraint Efk26 foreign key (`Child 2: Type of Care`) references CareType(careTypeId),
	`Child 3: Age` int,
    constraint Efk27 foreign key (`Child 3: Age`) references Age(ageId),
	`Child 4: Type of Care` int, 
    constraint Efk28 foreign key (`Child 4: Type of Care`)  references CareType(careTypeId),
	`Child 5: Age` int,
    constraint Efk29 foreign key (`Child 5: Age`) references Age(ageId),
	`Child 5: Type of Care` int,
    constraint Efk30 foreign key (`Child 5: Type of Care`) references CareType(careTypeId),
	`Transportation` bool,
	`Provisions for Disabilities` bool,
	`Translation` bool,
    `Translation: Between` int,
    constraint Efk31 foreign key (`Translation: Between`) references ServiceLanguage(serviceLanguageId),
	`Translation: And`int,
    constraint Efk32 foreign key (`Translation: And`) references ServiceLanguage(serviceLanguageId),
	`Interpretation` bool,
    `Interpretation: Between` int,
    constraint Efk33 foreign key (`Interpretation: Between`) references ServiceLanguage(serviceLanguageId),
	`Interpretation: And`int,
    constraint Efk34 foreign key (`Interpretation: And`) references ServiceLanguage(serviceLanguageId),
	`Crisis Counselling` bool,
    `Time Spent With Client/Addressing Client's Employment Needs: Hours` int check (`Time Spent With Client/Addressing Client's Employment Needs: Hours` >= 0 and `Time Spent With Client/Addressing Client's Employment Needs: Hours` <= 500),
    `Time Spent With Client/Addressing Client's Employment Needs: Minutes` int,
    constraint Efk35 foreign key (`Time Spent With Client/Addressing Client's Employment Needs: Minutes`) references TimeSpentMinutes(timeSpentMinutesId),
	`Reason for update` int,
    constraint Efk36 foreign key (`Reason for update`) references ReasonUpdate(reasonUpdateId));

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
	`templateId` int primary key,
    constraint IOfk1
		foreign key(templateId)
        references Template(templateId)
        on delete no action
        on update cascade,
	`Update Record ID` char(8),
	`Unique Identifier` int not null,
	constraint IOfk2 foreign key (`Unique Identifier`) references UniqueIdentifier(identifierId),
	`Unique Identifier Value` char(8) not null,
	`Date of Birth (YYYY-MM-DD)` date not null,
	`Postal Code where the service was received` char(6) not null,
	`Start Date of Service (YYYY-MM-DD)` date not null,
	`Language of Service` int not null,
    constraint IOfk3 foreign key (`Language of Service`) references ServiceLanguage(serviceLanguageId),
	`Official Language of Preference` int not null,
    constraint IOfk4 foreign key (`Official Language of Preference`) references LanguagePreference(preferLanguageId),
	`Type of Institution/Organization Where Client Received Services` int not null,
	constraint IOfk5 foreign key (`Type of Institution/Organization Where Client Received Services`) references InstitutionType(institutionTypeId),
	`Referred By`int not null,
	`Total Length of Orientation` int not null,
    constraint IOfk6 foreign key (`Total Length of Orientation`) references LengthOfOrientation(lengthOfOrientationId),
    `Total Length of Orientation: Hours` int check (`Total Length of Orientation: Hours` >= 0 and `Total Length of Orientation: Hours` <= 500),
	`Total Length of Orientation: Minutes`int,
    constraint IOfk7 foreign key (`Total Length of Orientation: Minutes`) references TimeSpentMinutes(timeSpentMinutesId),
	`Number of Clients in Group`int,
    constraint IOfk8 foreign key (`Number of Clients in Group`) references NumberClientsGroup(numberClientsGroupId),
    `Directed at a specific Target Group` bool,
	`Target Group: Children (0-14 yrs)` bool,
	`Target Group: Youth (15-24 yrs)` bool,
	`Target Group: Seniors` bool,
	`Target Group: Gender-specific` bool,
	`Target Group: Refugees` bool,
	`Target Group: Ethnic/cultural/linguistic group` bool,
	`Target Group: Deaf or Hard of Hearing` bool,
	`Target Group: Blind or Partially Sighted` bool,
	`Target Group: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)` bool,
	`Target Group: Families/Parents` bool,
	`Target Group: Clients with other impairments (physical, mental)` bool,
	`Target Group: Clients with international training in a regulated profession` bool,
	`Target Group: Clients with international training in a regulated trade` bool,
	`Target Group: Official Language minorities` bool,
	`Overview of Canada` bool,
	`Overview of Canada Referrals` bool,
	`Sources of Information` bool,
	`Sources of Information Referrals` bool,
	`Rights and Freedoms` bool,
	`Rights and Freedoms Referrals` int,
	`Canadian Law and Justice` bool,
	`Canadian Law and Justice Referrals` bool,
	`Important Documents` bool,
	`Important Documents Referrals` bool,
	`Improving English or French` bool,
	`Improving English or French Referrals` bool,
	`Employment and Income` bool,
	`Employment and Income Referrals` bool,
	`Education` bool,
	`Education Referrals` bool,
	`Housing` bool,
	`Housing Referrals` bool,
	`Health` bool,
	`Health Referrals` bool,
	`Money and Finances` bool,
	`Money and Finances Referrals` bool,
	`Transportation` bool,
	`Transportation Referrals` bool,
	`Communications and Media` bool,
	`Communications and Media Referrals` bool,
	`Community Engagement` bool,
	`Community Engagement Referrals` bool,
	`Becoming a Canadian Citizen` bool,
	`Becoming a Canadian Citizen Referrals` bool,
	`Interpersonal Conflict` bool,
	`Interpersonal Conflict Referrals` bool,
	`Was Essential Skills and Aptitude Training Received as Part of this Service?` bool not null,
	`Computer skills	Document Use` bool,
	`Interpersonal Skills and Workplace Culture` bool,
	`Leadership Training` bool,
	`Numeracy` bool,
	`Was Life Skills or Responsibilities of Citizenship Information Received as Part of this Service?` bool not null,
	`Life Skills` bool,
	`Rights and Responsibilities of Citizenship (based on discover Canada)` bool,
	`Support Services Received` bool not null,
	`Care for Newcomer Children` bool,
	`Child 1: Age` int,
    constraint IOfk9 foreign key (`Child 1: Age`) references Age(ageId),
	`Child 1: Type of Care` int,
    constraint IOfk10 foreign key (`Child 1: Type of Care`) references CareType(careTypeId),
	`Child 2: Age` int,
    constraint IOfk11 foreign key (`Child 2: Age`) references Age(ageId),
	`Child 2: Type of Care` int,
    constraint IOfk12 foreign key (`Child 2: Type of Care`) references CareType(careTypeId),
	`Child 3: Age` int,
    constraint IOfk13 foreign key (`Child 3: Age`) references Age(ageId),
	`Child 4: Type of Care` int, 
    constraint IOfk14 foreign key (`Child 4: Type of Care`)  references CareType(careTypeId),
	`Child 5: Age` int,
    constraint IOfk15 foreign key (`Child 5: Age`) references Age(ageId),
	`Child 5: Type of Care` int,
    constraint IOfk16 foreign key (`Child 5: Type of Care`) references CareType(careTypeId),
	`Transportation` bool,
	`Provisions for Disabilities` bool,
	`Translation` bool,
    `Translation: Between` int,
    constraint IOfk17 foreign key (`Translation: Between`) references ServiceLanguage(serviceLanguageId),
	`Translation: And`int,
    constraint IOfk18 foreign key (`Translation: And`) references ServiceLanguage(serviceLanguageId),
	`Interpretation` bool,
    `Interpretation: Between` int,
    constraint IOfk19 foreign key (`Interpretation: Between`) references ServiceLanguage(serviceLanguageId),
	`Interpretation: And`int,
    constraint IOfk20 foreign key (`Interpretation: And`) references ServiceLanguage(serviceLanguageId),
	`Crisis Counselling` bool,
	`End Date of Service (YYYY-MM-DD)` date not null,
	`Reason for update` int,
    constraint IOfk21 foreign key (`Reason for update`) references ReasonUpdate(reasonUpdateId));

#create table CommunityConnections();
#create table LTEnrolment();

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
create table VariableName(
	variableName varchar(30) primary key,
    realName varchar(255) not null);

insert into VariableName(variableName, realName) values ('CP1uniqueId', 'Unique Identifier');
