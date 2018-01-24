insert into TARGET(ID, URL) values ('50fgh81f105ebf1501605ebf47700000', 'www.google.com');
insert into TARGET(ID, URL) values ('50fgh81f105ebf1501605ebf47700001', 'www.facebook.com');
insert into TARGET(ID, URL) values ('50fgh81f105ebf1501605ebf47700002', 'www.this-url-seems-to-be-reaaaaaally-loooooong.com');
insert into TARGET(ID, URL) values ('50fgh81f105ebf1501605ebf47700003', 'www.this-url-seems-to-be-reaaaaaally-loooooong.mobile.com');

insert into SHORT_LINK(ID, URL) values ('50fgh81f105ebf1501605ebf47700100', 'www.ggl.com');
insert into SHORT_LINK(ID, URL) values ('50fgh81f105ebf1501605ebf47700101', 'www.fcbk.com');
insert into SHORT_LINK(ID, URL) values ('50fgh81f105ebf1501605ebf47700102', 'www.tustbrl.com');

insert into SHORT_LINK_TARGETS(SHORT_LINK_ID, TARGETS_ID) values ('50fgh81f105ebf1501605ebf47700100', '50fgh81f105ebf1501605ebf47700000');
insert into SHORT_LINK_TARGETS(SHORT_LINK_ID, TARGETS_ID) values ('50fgh81f105ebf1501605ebf47700101', '50fgh81f105ebf1501605ebf47700001');
insert into SHORT_LINK_TARGETS(SHORT_LINK_ID, TARGETS_ID) values ('50fgh81f105ebf1501605ebf47700102', '50fgh81f105ebf1501605ebf47700002');
insert into SHORT_LINK_TARGETS(SHORT_LINK_ID, TARGETS_ID) values ('50fgh81f105ebf1501605ebf47700102', '50fgh81f105ebf1501605ebf47700003');
