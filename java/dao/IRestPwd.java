package dao;

import entity.RestPwd;

import java.util.List;

public interface IRestPwd {
    public List<RestPwd> findAll();

    public RestPwd findById(String id);

    public boolean insert(RestPwd rp);

    public boolean update(RestPwd rp);

    public boolean delete(String id);

}
