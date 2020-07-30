package ru.bellintegrator.trainingproject.service.mapper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import ru.bellintegrator.trainingproject.model.User;
import ru.bellintegrator.trainingproject.view.user.UserView;

/**
 * Конвертер для преобразования из User в UserView
 */
public class UserConverter extends CustomConverter<User, UserView> {

    @Override
    public UserView convert(User user, Type<? extends UserView> type, MappingContext mappingContext) {
        UserView userView = new UserView();
            userView.setId(user.getId());
        if (user.getFirstName() != null) {
            userView.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userView.setLastName(user.getLastName());
        }
        if (user.getMiddleName() != null) {
            userView.setMiddleName(user.getMiddleName());
        }
        if (user.getPosition() != null) {
            userView.setPosition(user.getPosition());
        }
        if (user.getPhone() != null) {
            userView.setPhone(user.getPhone());
        }
        if (user.getCountries() != null) {
            if (user.getCountries().getCode() != null) {
                userView.setCitizenshipCode(user.getCountries().getCode());
            }
            if (user.getCountries().getName() != null) {
                userView.setCitizenshipName(user.getCountries().getName());
            }
        }
        if (user.getUserDoc() != null) {
            if (user.getUserDoc().getDocDate() != null) {
                userView.setDocDate(user.getUserDoc().getDocDate());
            }
            if (user.getUserDoc().getDocNumber() != null) {
                userView.setDocNumber(user.getUserDoc().getDocNumber());
            }
            if (user.getUserDoc().getDocs() != null) {
                userView.setDocName(user.getUserDoc().getDocs().getName());
            }
        }
        if (user.getIdentified() != null) {
            userView.setIdentified(user.getIdentified());
        }
        return userView;
    }
}
