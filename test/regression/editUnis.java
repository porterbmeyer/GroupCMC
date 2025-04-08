//package test.regression;
//
//private SystemController systemController;
//    private DatabaseController mockDB;
//    private User adminUser;
//
//    @BeforeEach
//    public void setUp() {
//        // Create a fake database controller
//        mockDB = mock(DatabaseController.class);
//        systemController = new SystemController(mockDB);
//        
//        // Create an admin user
//        adminUser = new User("admin1", "password", 'a', "Admin", "User");
//        
//        // Pretend there is a university in the database
//        when(mockDB.getUniDetails("TEST UNIVERSITY")).thenReturn(new University(
//            "Test University", "State", "Urban", "Public", 
//            5000, 50.0, 600, 650, 30000, 60.0, 
//            10000, 50.0, 40.0, 3, 4, 5
//        ));
//
//        // Pretend the database successfully updates the university
//        when(mockDB.university_editUniversity(any(), any(), any(), any(), anyInt(), anyDouble(), 
//                anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyInt(), anyDouble(), 
//                anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(1);
//    }
//
//    @Test
//    public void testEditUniversity() {
//        // This is the update request
//        String[] updates = { "Test University", "5500", "-1", "5", "-1", "-1" };
//
//        // Call the method we are testing
//        String result = systemController.editUniversity(updates, adminUser);
//
//        // Check if the result is correct
//        assertEquals("University details updated successfully.", result);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Clean up
//        systemController = null;
//        mockDB = null;
//        adminUser = null;
//    }
//}