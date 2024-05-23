package com.campusConnect.CampusConnect.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.campusConnect.CampusConnect.util.enums.SortType;

public interface CrudService<RQ, RS, ID> {
    public Page<RS> getAll(int page, int size, SortType sortType);

    public RS create(RQ request);

    public RS update(RQ request, ID id);

    public void delete(ID id);

    public RS getById(ID id);
}
