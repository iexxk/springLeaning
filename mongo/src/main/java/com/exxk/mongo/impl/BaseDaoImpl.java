package com.exxk.mongo.impl;

import com.exxk.mongo.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.util.ClassTypeInformation;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class BaseDaoImpl<T, ID> implements BaseDao<T, ID> {

    private SimpleMongoRepository<T, ID> mongoRepository;

    private Class<T> entityType;
    private Class<ID> identifierType;

    protected MongoTemplate mongoTemplate;

    public String table;

    public Boolean isCache = false;

    public BaseDaoImpl() {
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        entityType = (Class<T>) resolvableType.as(BaseDao.class).getGeneric(0).resolve();
        identifierType = (Class<ID>) resolvableType.as(BaseDao.class).getGeneric(1).resolve();
        table = entityType.getSimpleName() + ":";
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        MappingMongoEntityInformation<T, ID> entityInformation = new MappingMongoEntityInformation<T, ID>(
                new BasicMongoPersistentEntity<>(ClassTypeInformation.from(entityType)), identifierType);
        mongoRepository = new SimpleMongoRepository<T, ID>(entityInformation, mongoTemplate);
    }

    @Override
    public T findById(ID id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("Id").is(id.toString())), entityType);
    }


    @Override
    public void deleteAll() {
        mongoTemplate.remove(new Query(), entityType);
    }


    @Override
    public void deleteById(ID id) {
        mongoTemplate.remove(new Query(where("id").is(id)), entityType);
    }

    @Override
    public T findOne(T entity) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
                .withIgnorePaths("_class");
        Example<T> example = Example.of(entity, matcher);

        Optional<T> optional = mongoRepository.findOne(example);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public void enableCache(boolean isCache) {
        this.isCache = isCache;
    }

    @Override
    public <S extends T> S save(S entity) {
       return mongoTemplate.save(entity);
    }

}
