public class SocialMediaFriendConnections {
    private static class FriendNode {
        private final int friendId;
        private FriendNode next;

        private FriendNode(int friendId) {
            this.friendId = friendId;
        }
    }

    private static class User {
        private final int userId;
        private final String name;
        private final int age;
        private FriendNode friendHead;
        private User next;

        private User(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return userId + " | " + name + " | age=" + age;
        }
    }

    private static class SocialNetwork {
        private User head;

        private boolean addUser(int userId, String name, int age) {
            if (searchByUserId(userId) != null) {
                return false;
            }

            User user = new User(userId, name, age);

            if (head == null) {
                head = user;
                return true;
            }

            User current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = user;
            return true;
        }

        private boolean addFriendConnection(int firstUserId, int secondUserId) {
            if (firstUserId == secondUserId) {
                return false;
            }

            User firstUser = searchByUserId(firstUserId);
            User secondUser = searchByUserId(secondUserId);

            if (firstUser == null || secondUser == null) {
                return false;
            }

            if (containsFriend(firstUser, secondUserId)) {
                return false;
            }

            appendFriend(firstUser, secondUserId);
            appendFriend(secondUser, firstUserId);
            return true;
        }

        private boolean removeFriendConnection(int firstUserId, int secondUserId) {
            User firstUser = searchByUserId(firstUserId);
            User secondUser = searchByUserId(secondUserId);

            if (firstUser == null || secondUser == null) {
                return false;
            }

            boolean removedFirst = removeFriend(firstUser, secondUserId);
            boolean removedSecond = removeFriend(secondUser, firstUserId);
            return removedFirst && removedSecond;
        }

        private User searchByUserId(int userId) {
            User current = head;

            while (current != null) {
                if (current.userId == userId) {
                    return current;
                }

                current = current.next;
            }

            return null;
        }

        private User searchByName(String name) {
            User current = head;

            while (current != null) {
                if (current.name.equalsIgnoreCase(name)) {
                    return current;
                }

                current = current.next;
            }

            return null;
        }

        private String displayFriendsOfUser(int userId) {
            User user = searchByUserId(userId);

            if (user == null) {
                return "User not found";
            }

            if (user.friendHead == null) {
                return user.name + " has no friends";
            }

            StringBuilder builder = new StringBuilder();
            FriendNode current = user.friendHead;

            while (current != null) {
                User friend = searchByUserId(current.friendId);

                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                if (friend == null) {
                    builder.append(current.friendId);
                } else {
                    builder.append(friend.userId).append(" | ").append(friend.name);
                }

                current = current.next;
            }

            return builder.toString();
        }

        private String findMutualFriends(int firstUserId, int secondUserId) {
            User firstUser = searchByUserId(firstUserId);
            User secondUser = searchByUserId(secondUserId);

            if (firstUser == null || secondUser == null) {
                return "User not found";
            }

            StringBuilder builder = new StringBuilder();
            FriendNode current = firstUser.friendHead;

            while (current != null) {
                if (containsFriend(secondUser, current.friendId)) {
                    User mutualFriend = searchByUserId(current.friendId);

                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    if (mutualFriend == null) {
                        builder.append(current.friendId);
                    } else {
                        builder.append(mutualFriend.userId).append(" | ").append(mutualFriend.name);
                    }
                }

                current = current.next;
            }

            return builder.length() == 0 ? "No mutual friends" : builder.toString();
        }

        private String countFriendsForEachUser() {
            StringBuilder builder = new StringBuilder();
            User current = head;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.name).append(" -> ").append(countFriends(current));
                current = current.next;
            }

            return builder.toString();
        }

        private void appendFriend(User user, int friendId) {
            FriendNode node = new FriendNode(friendId);

            if (user.friendHead == null) {
                user.friendHead = node;
                return;
            }

            FriendNode current = user.friendHead;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }

        private boolean containsFriend(User user, int friendId) {
            FriendNode current = user.friendHead;

            while (current != null) {
                if (current.friendId == friendId) {
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        private boolean removeFriend(User user, int friendId) {
            if (user.friendHead == null) {
                return false;
            }

            if (user.friendHead.friendId == friendId) {
                user.friendHead = user.friendHead.next;
                return true;
            }

            FriendNode current = user.friendHead;

            while (current.next != null && current.next.friendId != friendId) {
                current = current.next;
            }

            if (current.next == null) {
                return false;
            }

            current.next = current.next.next;
            return true;
        }

        private int countFriends(User user) {
            int count = 0;
            FriendNode current = user.friendHead;

            while (current != null) {
                count++;
                current = current.next;
            }

            return count;
        }
    }

    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        network.addUser(1, "Aarav", 21);
        network.addUser(2, "Diya", 20);
        network.addUser(3, "Kabir", 22);
        network.addUser(4, "Meera", 23);

        network.addFriendConnection(1, 2);
        network.addFriendConnection(1, 3);
        network.addFriendConnection(2, 3);
        network.addFriendConnection(2, 4);

        System.out.println("Search By ID");
        System.out.println(network.searchByUserId(2));
        System.out.println();

        System.out.println("Search By Name");
        System.out.println(network.searchByName("Meera"));
        System.out.println();

        System.out.println("Friends Of User 2");
        System.out.println(network.displayFriendsOfUser(2));
        System.out.println();

        System.out.println("Mutual Friends Between 1 and 2");
        System.out.println(network.findMutualFriends(1, 2));
        System.out.println();

        network.removeFriendConnection(2, 3);
        System.out.println("Friend Counts");
        System.out.println(network.countFriendsForEachUser());
    }
}
