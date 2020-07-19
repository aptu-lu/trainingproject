package ru.bellintegrator.trainingproject.service.user;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.trainingproject.dao.user.UserDao;
import ru.bellintegrator.trainingproject.filter.UserFilter;
import ru.bellintegrator.trainingproject.model.User;
import ru.bellintegrator.trainingproject.view.ResponseData;
import ru.bellintegrator.trainingproject.view.SuccessResult;
import ru.bellintegrator.trainingproject.view.user.ListUserView;
import ru.bellintegrator.trainingproject.view.user.UserView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(UserDao userDao, MapperFacade mapperFacade) {
        this.userDao = userDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public ResponseData getList(UserFilter userFilter) {
        List<User> list = userDao.list(userFilter);
        List<ListUserView> listUserViews = list.stream()
                .map((user -> mapperFacade.map(user, ListUserView.class)))
                .collect(Collectors.toList());
        ResponseData responseData = new ResponseData();
        responseData.setData(listUserViews);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData get(int id) {
        User user = userDao.loadById(id);
        UserView userView = mapperFacade.map(user, UserView.class);
        ResponseData responseData = new ResponseData();
        responseData.setData(userView);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData update(UserFilter userFilter) {
        userDao.update(userFilter);
        ResponseData responseData = new ResponseData();
        responseData.setData(SuccessResult.RESULT);
        return responseData;
    }

    @Override
    @Transactional
    public ResponseData add(UserFilter userFilter) {
        User user = mapperFacade.map(userFilter, User.class);
        userDao.save(user);
        ResponseData responseData = new ResponseData();
        responseData.setData(SuccessResult.RESULT);
        return responseData;
    }
}
