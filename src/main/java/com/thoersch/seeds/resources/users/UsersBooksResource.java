package com.thoersch.seeds.resources.users;

import com.thoersch.seeds.persistence.users.UsersRepository;
import com.thoersch.seeds.representations.books.Book;
import com.thoersch.seeds.representations.users.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/users/{userId}/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UsersBooksResource {
    private final UsersRepository usersRepository;

    @Inject
    public UsersBooksResource(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GET
    public List<Book> getUsersBooks(@PathParam("userId") long userId) {
        User user = usersRepository.findOne(userId);

        if(user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return user.getBooks();
    }
}
