package com.crud.movies.crud.model.Repository;

/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.ParameterizedType;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Repository<T> {

    @PersistenceContext(name = "myDB")
    private EntityManager em;

    public T create(final T obj) {
        this.em.persist(obj);
        return obj;
    }

    public List<T> read() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        final Root<T> root = criteria.from(this.genericClass());
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    public void update(T obj) {
        this.em.merge(obj);
    }

    public void delete(T obj) {
        this.em.merge(obj);
    }

    public void deleteFull(Long id) {
        T obj = em.find(this.genericClass(), id);
        this.em.remove(obj);
    }

    public T find(Long id) {
        return em.find(this.genericClass(), id);
    }

    public T find(String hash) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where(builder.equal(root.get("hash"), hash));
        return em.createQuery(criteria).getSingleResult();
    }

    public Optional<T> find(String column, String value) {
        try {
            final CriteriaBuilder builder = em.getCriteriaBuilder();
            final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
            Root<T> root = criteria.from(this.genericClass());
            criteria.select(root).where(builder.equal(root.get(column), value));
            return Optional.of(em.createQuery(criteria).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    public T find(String column1, String value1, String column2, String value2) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where((builder.equal(root.get(column1), value1)),
                (builder.equal(root.get(column2), value2)));
        return em.createQuery(criteria).getSingleResult();
    }

    public T findUser(String value1, String value2) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where((builder.equal(root.get("value1"), value1)),
                (builder.equal(root.get("value2"), value2)));
        return em.createQuery(criteria).getSingleResult();
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public String generateHash() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[6];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String hash = encoder.encodeToString(bytes);
        return hash;
    }

    @SuppressWarnings("unchecked")
    private Class<T> genericClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}