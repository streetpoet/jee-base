INSERT INTO `${project.projectCode}db`.`users` (`id`,`userName`,`pwd`,`nickName`) VALUES (-1,'inner','l0m1DauymIBQdG85QIZMgw','全部');
INSERT INTO `${project.projectCode}db`.`users` (`id`,`userName`,`pwd`,`nickName`) VALUES (1,'streetpoet','o5y4DauymIBQdG85QIZMgw==','street poet');
INSERT INTO `${project.projectCode}db`.`userRoleRef` (`id`,`userId`,`roleId`) VALUES (1,1,1);
INSERT INTO `${project.projectCode}db`.`userRoleRef` (`id`,`userId`,`roleId`) VALUES (2,1,2);
INSERT INTO `${project.projectCode}db`.`userFamilyRef` (`id`,`userId`,`familyId`) VALUES (1,1,1);
INSERT INTO `${project.projectCode}db`.`roles` (`id`,`roleName`) VALUES (1,'Admin');
INSERT INTO `${project.projectCode}db`.`roles` (`id`,`roleName`) VALUES (2,'MemberRole');
INSERT INTO `${project.projectCode}db`.`family` (`id`,`familyName`,`description`) VALUES (1,'NSV',NULL);