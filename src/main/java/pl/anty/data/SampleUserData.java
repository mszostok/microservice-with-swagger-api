/**
 *  Copyright 2014 Reverb Technologies, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pl.anty.data;


import pl.anty.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SampleUserData {
  static List<User> users = new ArrayList<>();

  static {
    users.add(createUser(1, "us1", "firstName1", "lastName1","email1@test.com"));
    users.add(createUser(2, "us2", "firstName2", "lastName2","email2@test.com"));
    users.add(createUser(3, "us3", "firstName3", "lastName3","email3@test.com"));
    users.add(createUser(4, "us4", "firstName4", "lastName4","email4@test.com"));
    users.add(createUser(5, "us5", "firstName5", "lastName5","email5@test.com"));
    users.add(createUser(6, "us6", "firstName6", "lastName6","email6@test.com"));
    users.add(createUser(7, "us7", "firstName7", "lastName7","email7@test.com"));
    users.add(createUser(8, "us8", "firstName8", "lastName8","email8@test.com"));
    users.add(createUser(9, "us9", "firstName9", "lastName9","email9@test.com"));
  }

  private static User createUser(long id, String username, String firstName,
                                 String lastName, String email) {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(UUID.randomUUID().toString());
    return user;
  }

  public User findUserByName(String username) {
    return users.stream().filter(user -> user.getUsername().equals(username))
            .findFirst().orElse(null);
  }

  public void addUser(User user) {
    users.add(user);
  }

  public void updateUser(User user){
    if(users.removeIf(userOld -> userOld.getUsername().equals(user.getUsername()))){
      users.add(user);
    }
  }

  public void removeUser(String username) {
    users.removeIf(userOld -> userOld.getUsername().equals(username));
  }

}